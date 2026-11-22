# Ideias e brainstorms do NEXUS

**Tipo:** catálogo estratégico  
**Status:** consolidação inicial; estados sujeitos a revisão

Este documento é o catálogo central de ideias do NEXUS. Ele separa direções discutidas de funcionalidades confirmadas. Registros detalhados originados em experimentos permanecem nas pastas dos respectivos experimentos; este catálogo mantém o índice estratégico, sem duplicar evidências.

## 1. Experiência e organização

| Ideia / direção | Estado registrado | Observação |
|---|---|---|
| NEXUS como centro de comando pessoal e profissional | Consolidada | Visão central do produto |
| Home como centro de comando / Meu Dia | Consolidada | Resumo rápido, sem substituir módulos |
| Agenda com Dia, Semana, Mês e Lista | Consolidada como direção de UX | A implementação atual deve ser consultada em GOOGLE_IA |
| Abertura preferencial da Agenda na visão mensal, com dia atual selecionado | Registro histórico; direção em revisão | Após explorar Gabriel, o usuário prefere visão semanal inicial e considera uma escolha configurável; ver IDEA-001 pós-teste |
| Filtros compactos por tipo e categoria | Consolidada em registro de UX | Evitar controles redundantes |
| Pesquisa global acessível por controle compacto | Consolidada em registro de UX | Estado técnico a verificar |
| Muita informação com pouca confusão | Princípio consolidado | Evitar telas longas e excesso de carrosséis |
| Personalização de blocos da Home | Em análise / definição incompleta | Reordenar e ativar/desativar foram discutidos; regras finais não estão confirmadas |
| Onboarding em dois fluxos | Capturada / em análise | Tutorial para conhecer o app e fluxo separado para integrar dados; IDEA-003 |
| Central Financeira personalizável | Capturada / em análise | Reordenar/ocultar blocos e preservar visão especializada; IDEA-004 |
| Personalização independente do bloco Mercado no Financeiro | Capturada / em análise | Separar favoritos de exibição no Financeiro; IDEA-005 |
| Layouts adaptativos para tablets e telas maiores | Capturada / em análise | IDEA-006: reorganizar painéis conforme largura e orientação; Agenda em duas áreas no tablet deitado e avaliação de telas tipo monitor. Ver direção detalhada no pacote de rotação/identidade visual |
| Temas visuais harmonizados | Capturada / em análise | IDEA-007: usuário gostou da direção Grafite Suave, mas quer tonalidade um pouco mais cinza e confortável; paleta ainda em refinamento, sem valores finais aprovados. Ver [direção de refinamento do tema claro](PROPOSTAS/2026-11-21_ROTACAO_E_IDENTIDADE_VISUAL/DIRECAO_TEMA_CLARO.md) |
| Calendários de feriados BR e EUA | Capturada / em análise | IDEA-002 |
| Splash e animações discretas | Capturada / em análise | IDEA-008 e IDEA-009 |

## 2. Identidade visual e tipografia

| Ideia / direção | Estado registrado | Observação |
|---|---|---|
| Wordmark próprio NEXUS, monocromático e geométrico | Aprovada para exploração visual; identidade final não escolhida | Explorar segmentos, cortes e espaços negativos com desenho original; referência HUMANICA apenas como inspiração de linguagem, sem cópia. Ver [direção tipográfica](PROPOSTAS/2026-11-21_ROTACAO_E_IDENTIDADE_VISUAL/DIRECAO_IDENTIDADE_TIPOGRAFICA.md) |
| Tipografia de destaque em áreas selecionadas | Em exploração | Testar saudações e títulos; preservar fonte funcional em textos densos. Não implica criar família tipográfica completa nem editor complexo |
| Preservar ícone N branco sobre fundo preto | Diretriz de preservação nesta etapa | Não redesenhar nem substituir sem autorização separada |

## 3. Módulos e capacidades futuras discutidas

| Ideia | Estado registrado | Observação |
|---|---|---|
| Recorrência para compromissos | Planejada / pendente no último relatório | Não confundir com recorrência de tarefas já implementada |
| Recorrência para eventos | Planejada / pendente no último relatório | Requer exceções por ocorrência e prevenção de duplicidade |
| Evolução dos eventos com local e banner | Planejada / pendente no último relatório | Inclui modalidade de local e mídia |
| Pesquisa opcional de imagens para eventos | Capturada / em análise | IDEA-010; investigar licença, custos, armazenamento e backup |
| Backup ZIP com mídias | Planejada / pendente no último relatório | Complementaria o backup JSON existente |
| Integração financeira via Open Finance/importação | Capturada / análise de viabilidade | IDEA-011; manter operação essencial independente de serviços pagos |
| Assistente de IA para consultas e operações simples | Ideia futura / em avaliação | Não é requisito aprovado nem implementação confirmada |
| Integrações opcionais com Google/Android | Ideia futura | Não devem comprometer autonomia offline nem exigir conta |
| Personalização mais ampla do Dashboard | Em análise | Não antecipar decisões de comportamento |
| Refinamentos de widgets e notificações | Direção de evolução | Verificar estado técnico antes de propor novo trabalho |

## 4. Inteligência artificial no NEXUS

Foi discutida a possibilidade de um assistente simples, capaz de responder perguntas contextuais e executar operações limitadas sobre dados do próprio aplicativo — por exemplo, consultar quando uma parcela começa ou termina.

A ideia é tornar os dados acessíveis por linguagem natural sem transformar o aplicativo em um serviço dependente de IA paga. Ainda não há especificação aprovada de arquitetura, modelo, permissões, execução local ou custo. Status: **ideia futura / em avaliação**.

## 5. Gratuidade e sustentabilidade

A gratuidade do desenvolvimento e do uso essencial foi uma preocupação explícita. O Google AI Studio foi considerado uma ferramenta importante para iniciar o projeto, mas a estratégia é evitar dependência estrutural de serviços com cobrança por uso.

Isso não significa que todo provedor externo atual ou futuro terá disponibilidade garantida. Dependências, limites e alternativas devem ser documentados tecnicamente.

## 6. Origem e rastreabilidade das ideias pós-teste

As ideias IDEA-001 a IDEA-011 foram capturadas após a exploração do backup Gabriel. Seus contextos, ressalvas e detalhes estão em [Ideias de evolução — pós-teste](TESTES/2026-11-21_TESTE_VIDAS_SIMULADAS/05_OBSERVACOES_POS_TESTE/02_IDEIAS_DE_EVOLUCAO.md). O registro detalhado é a referência de origem; esta tabela funciona como índice.

As correções BUG-001 e COR-001 não são ideias de produto e permanecem em [Problemas e correções](TESTES/2026-11-21_TESTE_VIDAS_SIMULADAS/05_OBSERVACOES_POS_TESTE/01_PROBLEMAS_E_CORRECOES.md).

## 7. Regra de atualização

Ao surgir uma nova ideia:
1. Registrar contexto, origem e evidência.
2. Classificar o estado sem presumir aprovação.
3. Relacionar com ideias existentes e evitar duplicatas.
4. Manter o detalhe na fonte apropriada e usar este catálogo como índice.
5. Registrar alterações de direção e motivo, quando conhecido.
6. Atualizar documentos técnicos somente após decisão e implementação confirmadas.
