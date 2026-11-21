# Relatório — Geração e validação dos backups

**Data:** 21/11/2026  
**Origem:** relatório de execução fornecido pelo usuário, atribuído ao Google AI Studio.  
**Natureza:** registro do resultado reportado; não constitui auditoria independente do conteúdo dos arquivos.

## Contrato investigado segundo o relatório

- Backup JSON: `formatVersion = 7`.
- Banco: `databaseVersion = 6`.
- Aplicativo: `appVersion = 5.2.0`.
- Serialização: kotlinx.serialization JSON, com `ignoreUnknownKeys = true`, `coerceInputValues = true` e `encodeDefaults = true`.
- Payload: 27 tabelas Room e bloco opcional `marketConfig`.
- Restauração normaliza IDs singleton, trata referências inválidas conforme regras descritas no relatório e, após commit, reagenda lembretes válidos e notifica widgets.

O relatório diz que foram consultados modelos e gerenciadores de backup, repositório, banco e migrações, enums, motores de recorrência e agenda, regras financeiras, tarefas e lembretes, canais de notificação, modelos de mercado e utilitários de data/hora.

## Arquivos reportados

| Perfil | Arquivo | Tamanho reportado | Registros totais reportados |
|---|---|---:|---:|
| Gabriel | `NEXUS_TESTE_GABRIEL_UNIVERSITARIO.json` | 110.463 bytes | 193 |
| Helena | `NEXUS_TESTE_HELENA_COMUNIDADE.json` | 100.089 bytes | 166 |
| Ricardo | `NEXUS_TESTE_RICARDO_EMPRESARIO.json` | 121.753 bytes | 204 |
| **Total** | **3 arquivos independentes** | **332.305 bytes** | **563** |

Os totais são a soma dos registros dos três arquivos, não entidades únicas compartilhadas entre perfis.

## Cobertura reportada

- **Gabriel:** 5 projetos, 15 tarefas, 9 subtarefas, 8 compromissos, 2 conflitos, 71 transações, 2 cartões, 7 faturas, 1 parcelamento, 5 lembretes, 5 favoritos e 4 alertas.
- **Helena:** 3 projetos, 12 tarefas, 5 subtarefas, 5 compromissos, nenhum conflito, 72 transações, 1 cartão, 5 faturas, 1 parcelamento, 4 lembretes, 2 favoritos e 1 alerta.
- **Ricardo:** 5 projetos, 12 tarefas, 5 subtarefas, 7 compromissos, 1 conflito, 84 transações, 2 cartões, 9 faturas, 2 parcelamentos, 2 empréstimos, 5 lembretes, 6 favoritos e 5 alertas.

O relatório também descreve recorrências e exceções, metas automáticas e manuais, eventos pagos e gratuitos, briefings, personalização do Dashboard, compras parceladas, pendências financeiras e alertas de mercado. Detalhes específicos por perfil estão nos documentos de perfil.

## Validação estática reportada

O AI Studio reportou:

- JSON UTF-8 parseado sem erros nos três arquivos.
- Conferência das 32 chaves raiz, versões, DTOs, enums e JSON interno de configuração do Dashboard.
- IDs únicos por arquivo e entre arquivos, exceto singleton esperado.
- Integridade das referências listadas, restrições UNIQUE e coerência de valores pagos nas faturas.
- Validação dos códigos de ativos e unidades de alertas de mercado.
- Verificação de conflitos de agenda e coerência temporal centrada em 21/11/2026.
- Resultado declarado: `VALIDATION: PASS (0 errors)`, sem avisos.

O relatório informa uso de validação estática em Python (`/tmp/validate_nexus.py`).

## Limitações e itens não comprovados

- Não foi executada restauração real no aplicativo Android.
- Não houve emulador ou dispositivo Android disponível no ambiente de geração.
- Não foram executados testes instrumentados de APK.
- A presença dos arquivos no workspace/repositório do AI Studio foi reportada, mas a publicação no GitHub não está confirmada neste registro.
- O relatório informa que cache de cotações, séries históricas `market_history/` e configurações individuais de widgets Glance não integram o backup oficial v7.

## Próxima validação necessária

Restaurar cada JSON individualmente pela interface do aplicativo, após preservar os dados locais. Conferir prévia, conclusão da restauração, telas, relações, agenda, lembretes, finanças, metas, recorrências, Dashboard e recursos de mercado. Registrar resultados reais em [RELATORIO_TESTES_NO_APLICATIVO.md](RELATORIO_TESTES_NO_APLICATIVO.md).
