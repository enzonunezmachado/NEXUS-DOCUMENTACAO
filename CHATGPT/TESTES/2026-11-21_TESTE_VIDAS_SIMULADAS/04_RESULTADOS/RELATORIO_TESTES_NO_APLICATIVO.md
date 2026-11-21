# Relatório — Testes e exploração no aplicativo

**Estado geral:** exploração inicial de Gabriel realizada; Helena e Ricardo pendentes.  
**Data do relato de Gabriel:** 21/11/2026.

Este relatório registra o que o usuário efetivamente explorou no aplicativo. Não transforma uma experiência positiva inicial em auditoria completa.

## Gabriel

- **Arquivo:** NEXUS_TESTE_GABRIEL_UNIVERSITARIO.json
- **Repositório:** enzonunezmachado/PROJETO-NEXUS-, pasta test-backups/.
- **Restauração/exploração:** usuário confirmou que testou o perfil no aplicativo.
- **Resultado geral:** experiência inicial muito positiva.
- **Agenda:** gostou do fluxo de selecionar um dia e consultar os itens correspondentes abaixo. A quantidade e distribuição pareceram concisas e realistas, aproximadamente duas tarefas por dia e eventos ocasionais.
- **Visualização inicial:** a Agenda atualmente abre em visão mensal de aproximadamente 30 dias. O usuário prefere abertura semanal e considera oferecer escolha configurável entre Dia, Semana e Mês. Isso é solicitação de evolução, não comportamento já alterado.
- **Alertas:** elogiou a distinção visual entre vermelho (atrasos/pendências vencidas) e amarelo (conflitos/sobreposição de horários), exibidos na página inicial.
- **Observações detalhadas:** [Resultado da exploração do Gabriel](../05_OBSERVACOES_POS_TESTE/04_RESULTADO_EXPLORACAO_GABRIEL.md).
- **Problemas e correções identificados posteriormente:** [registro de problemas](../05_OBSERVACOES_POS_TESTE/01_PROBLEMAS_E_CORRECOES.md).
- **Limite:** não há confirmação de que todos os módulos, relações financeiras, recorrências, notificações ou fluxos do perfil tenham sido auditados.

## Helena

- **Arquivo:** NEXUS_TESTE_HELENA_COMUNIDADE.json
- **Dispositivo / versão do aplicativo:** não informado.
- **Restauração e exploração:** pendente de confirmação.
- **Resultado:** não registrado.

## Ricardo

- **Arquivo:** NEXUS_TESTE_RICARDO_EMPRESARIO.json
- **Dispositivo / versão do aplicativo:** não informado.
- **Restauração e exploração:** pendente de confirmação.
- **Resultado:** não registrado.

## Próximos critérios de observação

Nos perfis ainda não explorados, verificar se a restauração conclui sem erro; se os dados aparecem nas telas corretas; se relações, datas, valores e estados permanecem coerentes; se agenda e conflitos são apresentados corretamente; se recorrências, exceções e lembretes funcionam; se Dashboard e personalizações são carregados; e se os dados permanecem íntegros após fechar e reabrir o aplicativo.

Registrar comportamento observado, evidência reproduzível e impacto. Distinguir falha de restauração, inconsistência de dados, limitação de interface e oportunidade de produto.

## Registro de correções futuras

BUG-001 (perda de estado ao girar) e COR-001 (remoção do N verde) foram registrados como itens solicitados, ainda não implementados ou validados. Seus critérios de validação estão no documento de problemas e correções.
