# Propostas e instruções encaminhadas ao Google AI Studio

**Tipo:** registro de transferência entre estratégia e engenharia  
**Status:** consolidação inicial; inventário de prompts incompleto

## 1. Reorganização definitiva da documentação técnica
- **Data:** 20/11/2026 (conforme relatório recebido)
- **Estado:** Enviada; execução reportada como concluída no workspace AI Studio.
- **Objetivo:** reorganizar a documentação em `docs/`, documentar arquitetura real, dados, fluxos, design system, estado atual, qualidade, guia de desenvolvimento, roadmap, ADRs e rastreabilidade.
- **Restrições:** escopo documental; não alterar funcionalidades, UI, navegação, banco, migrations, build ou código funcional.
- **Requisitos adicionais:** preservar histórico, distinguir vigente de histórico, não declarar testes não executados, verificar portabilidade ZIP e realizar teste crítico de reconstrução independente.
- **Sincronização obrigatória:** consultar e atualizar artefatos internos do AI Studio, especialmente `NEXUS_CONTINUIDADE`, mantendo coerência com Markdown versionado.
- **Resultado reportado:** 12 documentos numerados, 14 ADRs, 26 requisitos rastreados, documentos antigos arquivados e artefatos internos atualizados.
- **Limitações reportadas:** build/testes e ZIP real não verificados; nenhum commit Git realizado no container.
- **Situação GitHub:** pendente de publicação/sincronização dos arquivos atualizados no repositório ativo.

## 2. Regra de manutenção documental por lote
- **Estado:** Aprovada e enviada como instrução permanente.
- **Regra:** todo lote de implementação deve consultar documentação, avaliar impacto, registrar mudanças reais, atualizar documentos afetados, registrar testes e limitações e sincronizar os artefatos internos relevantes.
- **Hierarquia:** código = implementação real; Markdown versionado = documentação oficial; artefatos internos = memória operacional complementar.

## 3. Geração de três backups para teste com vidas simuladas
- **Data:** 21/11/2026.
- **Estado:** encaminhada e executada no workspace, conforme relatório fornecido pelo usuário.
- **Objetivo:** criar três arquivos JSON independentes, compatíveis com o formato oficial de backup, representando Gabriel (universitário e assistência técnica), Helena (aposentada, comunidade, casas familiares e bolos) e Ricardo (empresário de distribuição, casado e sem filhos).
- **Autonomia solicitada:** investigar código e documentação reais, descobrir estruturas e regras suportadas e gerar dados abrangentes sem uma lista rígida de registros.
- **Restrições:** não alterar código, banco, DAOs, migrations, navegação, interface ou arquitetura; não inventar campos/formato; não sobrescrever dados pessoais; não publicar/commitar sem autorização.
- **Resultado reportado:** três JSONs em `/test-backups/`, formato v7, banco v6 e app 5.2.0; 563 registros somados; validação estática declarada PASS (0 errors).
- **Limitações:** restauração real no Android não executada; presença/publicação dos arquivos no GitHub ainda não confirmada.
- **Registro detalhado:** [pasta do experimento](TESTES/2026-11-21_TESTE_VIDAS_SIMULADAS/README.md).

## 4. Histórico de prompts anteriores
O repositório legado contém `docs/NEXUS_UPDATE_01_CONTROL_CENTER.md`, `docs/lista.md` e `docs/reviewchat.md`. Esses arquivos preservam instruções e discussões anteriores, mas não constituem inventário completo de todos os prompts enviados. Devem ser consultados antes de declarar que um prompt histórico foi perdido ou substituído.

## 5. Diagnóstico de rotação e exploração da identidade visual tipográfica
- **Identificador:** NEXUS-AI-2026-11-21-ROTACAO-IDENTIDADE-01.
- **Data:** 21/11/2026.
- **Estado:** prompt preparado e aprovado pelo usuário para encaminhamento; envio ao Google AI Studio ainda não confirmado.
- **Objetivo:** investigar o BUG-001 (perda de tela e estado ao girar o dispositivo) e explorar três direções originais de wordmark NEXUS, incluindo estudos de aplicação seletiva da tipografia na interface.
- **Preservações:** considerar os pontos positivos relatados no teste de Gabriel — fluxo da Agenda, densidade moderada de registros e alertas vermelhos para atrasos/amarelos para conflitos.
- **Direção visual:** wordmark monocromático, geométrico e original, explorando segmentos, cortes e espaços negativos. A referência HUMANICA é apenas inspiração de linguagem; não deve ser copiada. Preservar o ícone atual N branco sobre fundo preto.
- **Limites:** diagnóstico e protótipos isolados nesta etapa; não alterar código de produção, navegação, banco, fontes globais ou ícone; não implementar correções definitivas antes de revisão e autorização.
- **Pacote documental:** [prompt integral e direção tipográfica](PROPOSTAS/2026-11-21_ROTACAO_E_IDENTIDADE_VISUAL/README.md).
- **Resultado:** ainda não há diagnóstico técnico, protótipos, implementação ou validação. O prompt está preparado, não enviado.

## 6. Regra de registro futuro

Para cada prompt relevante, registrar:
- Identificador e data.
- Problema/contexto.
- Texto integral ou referência imutável.
- Escopo e restrições.
- Estado: preparado, aprovado, enviado, executado, parcialmente executado ou substituído.
- Relatório de resultado e limitações.
- Documentos técnicos afetados.

Não marcar como enviado apenas porque o prompt foi redigido. Não marcar como implementado apenas porque foi enviado.
