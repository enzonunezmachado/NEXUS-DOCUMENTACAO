# Registro do experimento — Três vidas simuladas

**Data de abertura:** 21/11/2026  
**Estado:** geração concluída segundo relatório recebido; validação no aplicativo pendente.

## Objetivo

Observar se o modelo de dados e as funcionalidades atuais do NEXUS conseguem representar rotinas pessoais, acadêmicas, comunitárias e empresariais distintas, com relações e situações realistas.

## Origem

A proposta surgiu da decisão de criar três perfis independentes, cada qual com um JSON de backup, para que o teste não ficasse limitado a um único tipo de usuário. O Google AI Studio recebeu uma instrução para investigar o aplicativo e escolher a melhor representação dentro das funcionalidades existentes, em vez de receber uma lista rígida de registros.

## Etapas e evidências

| Etapa | Estado | Evidência / limite |
|---|---|---|
| Definição dos três contextos humanos | Concluída | Perfis documentados nesta pasta |
| Investigação do contrato de backup e regras | Reportada como concluída | Lista de arquivos-fonte consultados no relatório de geração |
| Geração dos três JSONs | Reportada como concluída | Caminhos e tamanhos informados no relatório |
| Validação estática | Reportada como aprovada | JSON, estrutura, enums, IDs, referências e regras verificadas pelo AI Studio |
| Restauração real no Android | Pendente | Não havia emulador/dispositivo Android no ambiente informado |
| Observação de uso e interface | Pendente | Depende da restauração de cada perfil |

## Registro de observações

As observações devem ser numeradas (OBS-001, OBS-002...) e conter: perfil/arquivo, tela ou fluxo, comportamento observado, evidência reproduzível, impacto, classificação (problema técnico, dúvida, oportunidade ou comportamento esperado) e estado.

Uma observação não é automaticamente uma ideia. Antes de criar uma nova entrada no catálogo central, verificar se ela já está contemplada em `CHATGPT/03_IDEIAS_E_BRAINSTORMS.md`. Quando uma ideia realmente nova for identificada, relacioná-la à observação de origem.

## Próximas ações

1. Conferir se os três arquivos estão publicados no repositório ativo em `/test-backups/`.
2. Fazer backup dos dados pessoais atuais.
3. Restaurar Gabriel, explorar e registrar evidências.
4. Restaurar Helena, explorar e registrar evidências.
5. Restaurar Ricardo, explorar e registrar evidências.
6. Consolidar observações e avaliar se alguma merece investigação ou entrada no catálogo de ideias.

## Limites

Nenhum teste de restauração Android foi confirmado até o momento. Os resultados de geração e validação descritos aqui são atribuídos ao relatório fornecido pelo Google AI Studio, não a uma execução independente do ChatGPT.
