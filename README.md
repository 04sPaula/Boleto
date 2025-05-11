# Projeto Geração de Boletos
Este projeto é uma aplicação Java que permite a geração de boletos para diferentes bancos (Banco do Brasil, Itaú e Bradesco). A aplicação gera os boletos no formato PDF e inclui código de barras correspondente.
### Dependências
O projeto utiliza Maven para gerenciamento de dependências. Isso significa que todas as bibliotecas necessárias serão baixadas automaticamente ao compilar o projeto.
Dependências principais:
- `com.lowagie:itext` - Geração de PDFs.
- `net.sourceforge.barbecue:barbecue` - Geração de códigos de barras.

As versões específicas estão definidas no arquivo `pom.xml`.
### Como Rodar o Projeto
1. **Certifique-se de possuir os seguintes softwares instalados:**
    - [JDK 21](https://www.oracle.com/java/technologies/downloads/)
    - [Apache Maven](https://maven.apache.org/install.html)

2. **Compile o projeto:** No terminal, execute o seguinte comando na raiz do projeto:
``` bash
   mvn clean package
```
1. **Execute a aplicação:** Após a compilação, execute o arquivo `.jar` gerado, localizado na pasta `target`, com o seguinte comando:
``` bash
   java -jar target/<NOME_DO_ARQUIVO>.jar
```
Substitua `<NOME_DO_ARQUIVO>` pelo nome do arquivo `.jar` gerado.
1. **Siga as instruções no terminal:** A aplicação solicitará ao usuário as informações necessárias para gerar o boleto (ex.: banco, sacado, cedente, valor, e data de vencimento).
2. **PDF do boleto gerado:** O boleto será salvo na pasta `boletos/` no mesmo diretório do projeto.

### Observações
- O **Maven** gerencia todas as dependências automaticamente, mas caso encontre algum problema, use o comando `mvn dependency:resolve`.
- Certifique-se de que a pasta `imagens/` (para logotipos e códigos de barras) e `boletos/` existem ou serão criadas no caminho adequado.
