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

## 3. Histórico de prompts anteriores
O repositório legado contém `docs/NEXUS_UPDATE_01_CONTROL_CENTER.md`, `docs/lista.md` e `docs/reviewchat.md`. Esses arquivos preservam instruções e discussões anteriores, mas não constituem inventário completo de todos os prompts enviados. Devem ser consultados antes de declarar que um prompt histórico foi perdido ou substituído.

## 4. Regra de registro futuro

Para cada prompt relevante, registrar:
- Identificador e data.
- Problema/contexto.
- Texto integral ou referência imutável.
- Escopo e restrições.
- Estado: preparado, aprovado, enviado, executado, parcialmente executado ou substituído.
- Relatório de resultado e limitações.
- Documentos técnicos afetados.

Não marcar como enviado apenas porque o prompt foi redigido. Não marcar como implementado apenas porque foi enviado.
