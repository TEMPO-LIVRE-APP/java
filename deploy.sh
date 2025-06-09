#!/bin/bash
set -e

# Variáveis
RM="558253"
RESOURCE_GROUP="rg-springboot-rm$RM"
ACR_NAME="acrspringboot$RM"  # Nome deve ser minúsculo!
ACI_NAME="aci-springboot-rm$RM"
LOCATION="eastus"
IMAGE_NAME="tempo-livre:latest"

echo "🔍 Verificando instalação do Azure CLI..."
which az || curl -sL https://aka.ms/InstallAzureCLIDeb | sudo bash

echo "🔍 Verificando login no Azure CLI..."
az account show > /dev/null || az login

echo "📦 Criando Resource Group..."
az group create --name $RESOURCE_GROUP --location $LOCATION

echo "🏭 Criando Azure Container Registry..."
az acr create --resource-group $RESOURCE_GROUP \
  --name $ACR_NAME \
  --sku Basic \
  --admin-enabled true

echo "🔑 Obtendo credenciais do ACR..."
ACR_USERNAME=$(az acr credential show --name $ACR_NAME --query "username" -o tsv)
ACR_PASSWORD=$(az acr credential show --name $ACR_NAME --query "passwords[0].value" -o tsv)
ACR_LOGIN_SERVER=$(az acr show --name $ACR_NAME --query "loginServer" -o tsv)

echo "🐳 Login no Docker Registry..."
echo $ACR_PASSWORD | docker login $ACR_LOGIN_SERVER \
  --username $ACR_USERNAME \
  --password-stdin

echo "🔨 Construindo imagem Docker..."
docker build -t $ACR_LOGIN_SERVER/$IMAGE_NAME .

echo "🚀 Enviando imagem para ACR..."
docker push $ACR_LOGIN_SERVER/$IMAGE_NAME

echo "⚡️ Criando Container Instance..."
az container create \
  --resource-group $RESOURCE_GROUP \
  --name $ACI_NAME \
  --image $ACR_LOGIN_SERVER/$IMAGE_NAME \
  --registry-login-server $ACR_LOGIN_SERVER \
  --registry-username $ACR_USERNAME \
  --registry-password $ACR_PASSWORD \
  --dns-name-label $ACI_NAME \
  --ports 8080 \
  --environment-variables RM=$RM \
  --cpu 1 \
  --memory 2 \
  --os-type Linux

echo "✅ Implantação concluída!"
echo "🌐 URL da aplicação:"
FQDN=$(az container show --resource-group $RESOURCE_GROUP --name $ACI_NAME --query "ipAddress.fqdn" -o tsv)
echo "http://$FQDN:8080"