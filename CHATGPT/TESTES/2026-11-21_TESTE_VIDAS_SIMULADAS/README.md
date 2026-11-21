# Experimento — Três vidas simuladas no NEXUS

**Data de referência:** 21/11/2026  
**Tipo:** teste exploratório de cobertura e adequação do produto com dados fictícios  
**Estado:** backups gerados e validados estaticamente segundo relatório do Google AI Studio; restauração real no aplicativo pendente.

## Objetivo

Avaliar como o NEXUS representa três contextos de vida muito diferentes, usando arquivos JSON independentes no formato oficial de backup. O experimento procura exercitar funcionalidades existentes com dados variados e relações plausíveis, sem transformar os perfis em requisitos de produto.

## Conteúdo

- [Prompt de criação](01_PROMPT/PROMPT_CRIACAO_TRES_PERFIS.md) — reconstrução fiel do escopo e das instruções; não é transcrição literal do prompt original.
- [Gabriel — universitário](02_PERFIS/GABRIEL_UNIVERSITARIO.md)
- [Helena — comunidade](02_PERFIS/HELENA_COMUNIDADE.md)
- [Ricardo — empresário](02_PERFIS/RICARDO_EMPRESARIO.md)
- [Registro do experimento](03_ACOMPANHAMENTO/REGISTRO_DO_EXPERIMENTO.md)
- [Relatório de geração e validação](04_RESULTADOS/RELATORIO_GERACAO_E_VALIDACAO.md)
- [Relatório de testes no aplicativo](04_RESULTADOS/RELATORIO_TESTES_NO_APLICATIVO.md) — aguardando execução.

## Arquivos JSON

O relatório do Google AI Studio informa que os arquivos foram criados em `/test-backups/` no repositório ativo do aplicativo:

- `NEXUS_TESTE_GABRIEL_UNIVERSITARIO.json`
- `NEXUS_TESTE_HELENA_COMUNIDADE.json`
- `NEXUS_TESTE_RICARDO_EMPRESARIO.json`

Os JSONs não são duplicados neste repositório documental. A presença e publicação deles no GitHub ainda precisam ser conferidas.

## Como executar o teste no aplicativo

1. Preserve o backup atual do usuário antes de qualquer restauração.
2. Transfira apenas um JSON de teste por vez para o dispositivo.
3. No NEXUS, abra **Mais → Backup e Restauração → Restaurar backup (JSON)**.
4. Selecione o arquivo, confira a prévia e só confirme sabendo que a restauração substitui os dados locais.
5. Explore o perfil restaurado e registre evidências, problemas e observações.
6. Antes de testar outro perfil, preserve novamente qualquer dado que queira manter.

A restauração deve ser feita isoladamente para cada perfil. Não importar os três em sequência esperando que os dados sejam mesclados.

## Limites do experimento

- Os perfis e registros são fictícios.
- A validação estática não equivale a uma restauração real no Android.
- Observações não se tornam automaticamente ideias, decisões ou requisitos.
- Problemas técnicos devem ser separados de oportunidades de produto.
- Nenhuma mudança no código do aplicativo é autorizada ou registrada por esta documentação.
