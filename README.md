Problema comum

Ao importar o projeto no Eclipse, podem aparecer erros como:

The value of the field is not used
Retornos JSON vazios ({})
Getters/Setters não funcionando

Isso acontece porque o Lombok não vem habilitado automaticamente no Eclipse.

Como resolver
Baixe o arquivo .jar do Lombok:
https://projectlombok.org/download

Execute o instalador:

java -jar lombok.jar
Na janela que abrir:
Clique em "Specify location"
Selecione a pasta onde o Eclipse está instalado
Clique em Install / Update
Reinicie o Eclipse