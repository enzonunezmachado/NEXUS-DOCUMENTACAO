# Registro do experimento — Três vidas simuladas

**Data de abertura:** 21/11/2026  
**Estado:** backups gerados e validados estaticamente segundo relatório recebido; publicação dos JSONs no repositório ativo conferida; exploração inicial de Gabriel realizada pelo usuário; Helena e Ricardo pendentes.

## Objetivo

Observar se o modelo de dados e as funcionalidades atuais do NEXUS conseguem representar rotinas pessoais, acadêmicas, comunitárias e empresariais distintas, com relações e situações realistas.

## Origem

A proposta surgiu da decisão de criar três perfis independentes, cada qual com um JSON de backup, para que o teste não ficasse limitado a um único tipo de usuário. O Google AI Studio recebeu uma instrução para investigar o aplicativo e escolher a melhor representação dentro das funcionalidades existentes, em vez de receber uma lista rígida de registros.

## Etapas e evidências

| Etapa | Estado | Evidência / limite |
|---|---|---|
| Definição dos três contextos humanos | Concluída | Perfis documentados nesta pasta |
| Investigação do contrato de backup e regras | Reportada como concluída | Lista de arquivos-fonte consultados no relatório de geração |
| Geração dos três JSONs | Reportada como concluída | Relatório de geração |
| Validação estática | Reportada como aprovada | JSON, estrutura, enums, IDs, referências e regras verificados pelo AI Studio |
| Publicação dos JSONs | Conferida no GitHub | Três arquivos presentes em PROJETO-NEXUS-/test-backups/ |
| Restauração/exploração de Gabriel | Realizada pelo usuário | Relato positivo de Agenda, densidade de dados e alertas; ver resultado pós-teste |
| Restauração/exploração de Helena | Pendente | Nenhum resultado recebido |
| Restauração/exploração de Ricardo | Pendente | Nenhum resultado recebido |
| Auditoria funcional completa | Pendente | O teste de Gabriel foi exploração inicial, não cobertura integral |

## Registro de observações

As observações deste experimento estão organizadas em [05_OBSERVACOES_POS_TESTE](../05_OBSERVACOES_POS_TESTE/README.md). Use IDs estáveis:
- BUG para defeitos observados;
- COR para correções solicitadas;
- OBS para comportamentos positivos ou fatos de uso;
- IDEA para oportunidades de produto;
- FUT para possibilidades de longo prazo.

Cada observação deve indicar origem, perfil/arquivo, tela ou fluxo, comportamento, evidência reproduzível, impacto, estado e próximo passo. Uma observação não é automaticamente uma ideia.

## Próximas ações

1. Preservar os dados pessoais locais antes de cada restauração.
2. Explorar Helena e Ricardo, se o usuário decidir continuar.
3. Analisar tecnicamente BUG-001 e COR-001 antes de implementar.
4. Consolidar as ideias pós-teste em análise técnica, sem presumir aprovação.
5. Atualizar os estados dos registros quando houver evidência de proposta, aprovação, implementação e validação.

## Limites

Os resultados de geração e validação estática são atribuídos ao relatório fornecido pelo Google AI Studio, não a uma execução independente do ChatGPT. O resultado de Gabriel deriva do relato do usuário. Nenhum teste automatizado de rotação ou correção visual foi executado por esta atualização documental.
