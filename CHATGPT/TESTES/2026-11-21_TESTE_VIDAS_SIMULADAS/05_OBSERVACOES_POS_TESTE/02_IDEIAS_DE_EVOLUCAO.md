# Ideias de evolução identificadas após a exploração

**Estado geral:** CAPTURADAS / EM ANÁLISE. Nenhuma ideia abaixo está aprovada para implementação apenas por constar neste documento.

As ideias surgiram principalmente da experiência com o perfil Gabriel. A referência ao teste não significa que cada funcionalidade foi testada em profundidade.

## IDEA-001 — Visualização inicial da Agenda configurável

- **Estado:** CAPTURADA / EM ANÁLISE.
- **Necessidade:** a Agenda atualmente abre na visualização mensal, cobrindo aproximadamente 30 dias. O usuário prefere que a abertura inicial seja semanal.
- **Direção a avaliar:** permitir escolher nas configurações entre Dia, Semana e Mês como visualização inicial. Manter as visualizações existentes e o comportamento de selecionar uma data e consultar seus itens.
- **Não fazer:** remover a visão mensal ou alterar a semântica da Agenda sem análise.

## IDEA-002 — Calendários de feriados do Brasil e dos Estados Unidos

- **Estado:** CAPTURADA / EM ANÁLISE.
- **Necessidade:** exibir feriados e datas relevantes de ambos os países, com calendários ativáveis independentemente ou em conjunto.
- **Exemplos:** Consciência Negra, Ação de Graças, Independence Day e Natal.
- **Analisar:** feriados nacionais, estaduais e municipais; datas móveis; fontes oficiais; atualização; APIs gratuitas; alternativa local/offline; privacidade e manutenção.
- **Direção:** estudar uma camada de calendário/visualização. Não criar automaticamente tarefas ou compromissos artificiais para cada feriado.

## IDEA-003 — Onboarding em dois fluxos independentes

- **Estado:** CAPTURADA / EM ANÁLISE.
- **Fluxo A — conhecer o NEXUS:** tutorial inicial sobre propósito, abas, Agenda, tarefas, eventos, compromissos, projetos, finanças e alertas; com avançar, voltar e pular.
- **Fluxo B — integrar a vida ao NEXUS:** orientação prática para cadastrar os primeiros dados e entender diferenças e relações entre conceitos.
- **Direção:** reduzir a fricção da entrada de dados e evitar um formulário extenso obrigatório. Uma experiência guiada tradicional pode ser suficiente; IA não é requisito.

## IDEA-004 — Central Financeira personalizável

- **Estado:** CAPTURADA / EM ANÁLISE.
- **Visão:** o Financeiro deve ter importância equivalente à página inicial, mas com profundidade própria. A Home oferece visão integrada; Financeiro oferece visão abrangente da vida financeira.
- **Personalização desejada:** reordenar blocos, escolher o que aparece primeiro, ocultar componentes e restaurar uma organização padrão.
- **Exemplos de blocos a avaliar conforme o que já existe:** saldo, receitas/despesas, fluxo de caixa, contas próximas, cartões/faturas, orçamentos, metas, empréstimos, indicadores e gráficos.
- **Analisar:** reutilização do mecanismo de personalização da Home, persistência, responsividade, backup e restauração.

## IDEA-005 — Personalização independente do bloco Mercado no Financeiro

- **Estado:** CAPTURADA / EM ANÁLISE.
- **Problema a investigar:** o usuário já consegue alterar favoritos no módulo Mercado, mas não sabe se consegue ocultar a seção ou controlar o que aparece no Financeiro.
- **Separação obrigatória de conceitos:**
  - favoritos = ativos acompanhados no módulo Mercado;
  - exibição no Financeiro = seleção e apresentação de ativos no bloco da central financeira.
- **Comportamento desejado:** ocultar o bloco inteiro sem apagar dados; remover um ativo da visualização financeira sem removê-lo dos favoritos, histórico, alertas ou módulo Mercado.
- **Personalização desejada:** escolher ativos; escolher apresentação por ativo (por exemplo, dólar com gráfico pequeno, Bitcoin/ETH em formato compacto); grade compacta com 1, 2 ou 3 colunas; formato horizontal de largura inteira; avaliar listas ou combinações.
- **Organização:** preservar separações/categorias existentes para moedas e cripto se isso continuar claro.
- **Interação:** clicar em um ativo deve abrir a tela de detalhes existente, com histórico, gráficos e períodos.
- **Analisar:** recursos atuais, armazenamento das preferências, dimensionamento adaptativo, persistência e backup. Não presumir que a funcionalidade já exista ou que o desenho final esteja decidido.

## IDEA-006 — Layouts adaptativos para celular, tablet e telas maiores

- **Estado:** CAPTURADA / EM ANÁLISE.
- **Necessidade:** reorganizar componentes conforme largura, altura e orientação, não apenas esticar a interface de celular.
- **Agenda em tablet deitado:** avaliar duas áreas lado a lado: calendário/dias da semana em uma e itens do dia selecionado na outra. Selecionar outro dia atualiza o painel de itens sem perder o contexto. A proporção entre painéis deve ser estudada, não fixada automaticamente em 50/50.
- **Agenda em celular:** cartões da visão semanal devem aproveitar a altura útil disponível de forma proporcional, considerando barras do sistema, orientação e diferentes proporções de tela. Evitar cartões comprimidos ou excessivamente altos.
- **Contornos:** na exploração visual, aumentar a espessura dos contornos de alertas e elementos destacados, tomando como referência o contorno verde do dia selecionado na visão semanal. Não fixar valor numérico antes da comparação e da verificação de acessibilidade.
- **Telas tipo monitor:** estudar painéis simultâneos e densidade adequada, sem simplesmente ampliar componentes móveis. Isso não significa aprovar suporte desktop completo.
- **Analisar:** Compose adaptive layouts/window size classes, orientação, tamanhos de janela, acessibilidade e preservação das preferências pessoais.
- **Regra:** manter dados, seleção e contexto ao mudar a composição. O número de colunas e a proporção dos painéis devem ser adaptativos.
- **Separação do BUG-001:** layout adaptativo trata da composição visual; a correção de rotação deve preservar rota, dados de formulários, seleções e progresso durante a recriação da Activity. Uma frente não substitui a outra.
- **Referência detalhada:** [Direção de layouts adaptativos](../../PROPOSTAS/2026-11-21_ROTACAO_E_IDENTIDADE_VISUAL/DIRECAO_LAYOUTS_ADAPTATIVOS.md).

## IDEA-007 — Temas visuais personalizáveis e harmonizados

- **Estado:** CAPTURADA / EM ANÁLISE.
- **Necessidade:** oferecer alternativas visuais sem criar combinações de cores ruins ou aumentar excessivamente a complexidade.
- **Alternativas a comparar:** temas completos pré-harmonizados; personalização limitada de cores; paletas derivadas automaticamente.
- **Preservar:** contraste, acessibilidade e semântica visual dos estados — especialmente vermelho para atraso e amarelo para conflito.
- **Não assumir:** controles livres para primária, secundária e terciária como solução final.

## IDEA-008 — Splash screen e entrada animada

- **Estado:** CAPTURADA / EM ANÁLISE.
- **Necessidade:** substituir a tela preta percebida na abertura por uma apresentação coerente com a identidade NEXUS.
- **Direção:** usar splash screen compatível com Android e uma transição/animação curta de entrada após a inicialização, quando apropriado.
- **Restrições:** não criar espera falsa nem atrasar a abertura apenas para simular carregamento; não confundir abertura do app com execução automática durante o boot do aparelho.

## IDEA-009 — Animações discretas na interface

- **Estado:** CAPTURADA / EM ANÁLISE.
- **Exemplos:** gráficos crescendo ao carregar, indicadores e barras preenchendo, transições suaves e componentes aparecendo progressivamente.
- **Analisar:** Jetpack Compose/Material 3, fluidez, consumo de bateria, dispositivos modestos, acessibilidade e preferência por redução de movimento.
- **Direção:** animação funcional e moderada, não decoração excessiva.

## IDEA-010 — Imagens em eventos com pesquisa opcional

- **Estado:** CAPTURADA / EM ANÁLISE.
- **Necessidade:** ao criar/editar um evento, oferecer pesquisa de imagens relacionadas ao nome, seleção entre opções e também escolha de imagem local.
- **Analisar:** APIs e fontes, custos/limites, licenças e uso comercial, armazenamento local, compressão, backup/restauração e eventual pacote de backup com mídias.
- **Restrição:** resultados encontrados em mecanismos de busca não são automaticamente livres para uso.

## IDEA-011 — Integração financeira por Open Finance ou importação

- **Estado:** CAPTURADA / EM ANÁLISE DE VIABILIDADE; dependências externas podem tornar a ideia inviável para o núcleo gratuito.
- **Visão:** com autorização explícita do usuário, obter movimentações bancárias/cartões, reconhecer estabelecimento, valor e data, sugerir categoria e recorrência (por exemplo, Spotify), solicitar dados faltantes e permitir confirmação/correção.
- **Analisar:** Open Finance Brasil, instituições e intermediários suportados, custos, consentimento, segurança, expiração de autorização, duplicidade, estornos, parcelas, transações pendentes e separação pessoal/empresarial.
- **Alternativa:** importação manual de extratos em formatos suportados.
- **Restrições essenciais:** nunca armazenar senhas bancárias nem contornar políticas das instituições; manter o NEXUS útil sem assinatura de IA, conexão obrigatória ou serviço pago.

## Observações transversais

- A experiência da Agenda e os alertas diferenciados são comportamentos positivos a preservar.
- O mecanismo de personalização da Home pode ser reutilizado, mas isso precisa ser verificado no código.
- Antes de transformar uma ideia em proposta, investigar o que já existe e consultar documentação técnica vigente.
- A lista representa desejos e hipóteses do usuário, não especificação final nem autorização para implementar.
