# Comparação crítica — propostas de arquitetura da informação do NEXUS

**Data:** 24/11/2026  
**Estado:** EM ANÁLISE  
**Escopo:** leitura comparativa dos três relatórios preservados nesta pasta.  
**Autoridade:** síntese analítica, não decisão aprovada e não especificação técnica.

## 1. Conclusão executiva

A proposta do **Claude** é a base mais forte desta rodada para continuar a análise de arquitetura da informação. Ela apresenta um modelo mental claro, uma navegação principal coerente, um mapa de telas, regras transversais, fluxos ponta a ponta, adaptação para tablets, trade-offs e um plano gradual de transição. Seu maior mérito é organizar o produto por perguntas e pela natureza dos registros, em vez de apenas reorganizar a posição dos módulos atuais.

A **Etapa 3** oferece uma boa exploração de conceitos visuais e relacionais, especialmente a ideia de profundidade progressiva e de conexões vivas. A proposta Nexus Synapse tem elementos aproveitáveis, mas o relatório mistura conceitos interessantes com alegações técnicas e escopos que não estão suficientemente demonstrados.

O **relatório preparatório** é valioso como leitura estratégica do propósito do produto e como catálogo de oportunidades, mas é o documento que mais exige revalidação de premissas técnicas, versões, custos e disponibilidade de APIs antes de qualquer reutilização.

**Recomendação de trabalho:** usar Claude como hipótese estrutural principal, recuperar ideias específicas dos outros dois relatórios e submetê-las a crítica contra o aplicativo real, documentação técnica e experiência de uso. Não adotar a proposta inteira de forma automática.

## 2. Avaliação lado a lado

| Critério | Claude | Etapa 3 / Synapse | Relatório preparatório |
|---|---|---|---|
| Clareza do modelo mental | Muito forte: cinco perguntas e domínios/lentes/contêineres | Forte na ideia de nós e conexões, menos uniforme entre alternativas | Forte na visão de produto |
| Arquitetura de navegação | Mais completa e justificável | Quatro direções, mas com diferenças de maturidade | Não fecha uma navegação detalhada |
| Mapa de telas | Abrangente e rastreável | Mais conceitual | Não é o foco |
| Fluxos ponta a ponta | Desenvolvidos | Demonstra profundidade com exemplo | Oportunidades descritas, poucos fluxos completos |
| Progressive disclosure | Estruturado em níveis e por tela | Ideia central bem comunicada | Aparece como princípio geral |
| Tablet e telas grandes | Adaptativo por destino e classes de layout | Ambicioso, com split view e DeX | Menos aprofundado |
| Separação atual/novo/hipótese | Muito boa e explícita | Parcial; algumas sugestões parecem fatos | Variável |
| Rigor técnico | Cauteloso em boa parte; pede validação de pontos incertos | Algumas afirmações excedem as evidências apresentadas | Exige revalidação ampla |
| Plano de transição | Presente, gradual e faseado | Próximos passos menos operacionais | Orientações gerais |

## 3. O que torna a proposta do Claude especialmente interessante

### 3.1 Organiza por perguntas reais

A estrutura Hoje · Agenda · Tarefas · Planos · Dinheiro é sustentada por cinco perguntas:

- Hoje: “E agora?”
- Agenda: “Quando?”
- Tarefas: “O que fazer?”
- Planos: “Para quê, e que ocasiões?”
- Dinheiro: “Quanto?”

Isso cria um critério compreensível para decidir onde cada área mora. Também dá uma justificativa para Projetos e Objetivos deixarem de ficar escondidos dentro de Tarefas ou do menu secundário, e para Eventos serem tratados como um tipo de plano.

### 3.2 Distingue domínios de lentes

A ideia de que Agenda, Hoje e Busca são lentes que apresentam registros de outras áreas — sem criar cópias — é muito compatível com a lógica de registrar uma vez e visualizar em vários contextos. A proposta também reforça que cada registro deve ter uma ficha de origem, aberta a partir de qualquer lugar.

### 3.3 Dá linguagem às conexões

Dois padrões merecem atenção especial:

- **Chip de origem:** explica por que aquele item aparece naquela tela e de onde veio.
- **Faixa de conexões:** apresenta relações dentro da ficha usando verbos compreensíveis, como “parte de”, “custa”, “prepara”, “contribui para” e “vence em”.

Essas ideias podem tornar as integrações perceptíveis sem obrigar a pessoa a entender o modelo de dados.

### 3.4 Separa superfície diária de exploração profunda

A proposta de Hoje como leitura rápida, com um esqueleto estável e um Radar configurável, tenta resolver o conflito entre uma Home útil e uma Home excessivamente carregada. A ideia de deixar análises completas nos módulos de origem, mantendo atalhos a partir de resumos, também merece prototipagem.

### 3.5 Trata tablet como outro arranjo, não como celular esticado

A adaptação por destino — lista e detalhe, calendário e painel do dia, área principal e painel de apoio — é mais útil do que simplesmente aumentar cards ou colunas sem critério.

## 4. Ideias da Etapa 3 que vale preservar

1. **Profundidade progressiva em cinco níveis:** resumo → módulo → filtro analítico → ficha do registro → navegação para registros relacionados.
2. **Conexões vivas:** a pessoa entende como uma tarefa, evento, projeto e despesa se relacionam.
3. **Constellation:** explorar uma visualização relacional para iniciativas e registros conectados, sem necessariamente torná-la navegação principal.
4. **Matrix Dual:** considerar painéis paralelos em tablets e telas grandes, especialmente quando a pessoa precisa consultar uma lista enquanto inspeciona um detalhe.
5. **Nexus Synapse:** preservar a tese de Home sintética + módulos especializados de alta densidade, mas não tratar a implementação descrita como tecnicamente validada.

A alternativa Horizon Chrono deve ser explorada com cuidado: tempo é uma lente importante, mas nem todos os registros têm data ou devem ser forçados para uma linha do tempo.

## 5. Ideias do relatório preparatório que vale preservar

1. **“O sistema pessoal invisível”** como hipótese de posicionamento: o sistema cuida da complexidade sem exigir administração constante.
2. **Poder com leveza:** motor interno rigoroso, superfície clara e agradável.
3. **Personalidade sem ruído:** Biblioteca de Frases e detalhes de identidade preservados sem transformar o produto em um painel frio.
4. **Captura contextual por linguagem natural:** oportunidade futura, desde que o usuário revise e confirme o que será criado.
5. **Importação local de extratos:** hipótese de conveniência que merece avaliação de formato, segurança, duplicidade e esforço de implementação.
6. **Imagens em Eventos:** possibilidade de enriquecer a experiência, mantendo opção local e avaliando licenças, custos e dependências antes de escolher provedores.

As afirmações de preços, limites de APIs, disponibilidade de modelos, requisitos de dispositivos e viabilidade de integrações não devem ser reutilizadas sem pesquisa atualizada.

## 6. Riscos e pontos que não devem ser adotados automaticamente

### Na proposta do Claude

- **Cinco destinos principais:** é uma hipótese forte, mas precisa ser testada contra frequência de uso, descoberta e importância cotidiana de Eventos e Finanças.
- **Eventos dentro de Planos:** conceitualmente coerente, mas pode reduzir a descoberta de Eventos se eles forem usados com frequência.
- **Mercado dentro de Dinheiro:** depende do modelo mental do usuário; Mercado é informação externa, não patrimônio pessoal.
- **Hoje com esqueleto fixo:** pode melhorar consistência, mas não deve limitar personalização útil nem obrigar todos a ver os mesmos blocos.
- **Atenção e notificações:** a regra de coerência proposta é interessante, mas não se deve assumir que todo alerta precisa virar notificação ou que toda notificação deve ocupar a Home.
- **Comprometido em doze meses:** exige conferir se as regras atuais permitem consolidar faturas, parcelas, recorrências e empréstimos sem dupla contagem.
- **Criação em um único fluxo:** parcelamento e recorrência podem ter regras próprias; a simplificação visual não pode esconder decisões financeiras importantes.

### Na Etapa 3

- O texto afirma compatibilidade total com Room v6, FinanceRules e AgendaEngine sem apresentar uma validação técnica que sustente essa garantia.
- O perfil de “secretária que administra terceiros” amplia o público e o modelo de permissões sem estar estabelecido como objetivo do produto.
- O protótipo HTML/Canvas mencionado não foi inspecionado nesta análise; sua existência ou fidelidade não deve ser tratada como evidência.
- Alguns exemplos apresentam informações, imagens, convidados, comprovantes ou capacidades que podem não existir no produto atual.

### No relatório preparatório

- Há referências a versões, schema, APIs, limites e custos que podem ter envelhecido ou não corresponder ao estado técnico atual.
- A afirmação de que uma oportunidade é “baixa complexidade” não substitui análise de impacto em arquitetura, dados, manutenção e experiência.
- Sugestões de IA local ou em nuvem não alteram por si só as decisões atuais de privacidade, offline-first ou governança.

## 7. Síntese de direção para a próxima rodada

A próxima rodada não precisa escolher um documento inteiro. Deve combinar os elementos que melhor resolvem problemas reais:

| Tema | Hipótese prioritária para explorar | Fonte principal |
|---|---|---|
| Navegação principal | Cinco destinos por pergunta, comparados com uma alternativa de quatro destinos | Claude |
| Home | Hoje sintético, com leitura imediata e profundidade opcional | Claude + Synapse |
| Relações | Chip de origem e faixa de conexões com linguagem natural | Claude + Etapa 3 |
| Profundidade | Resumo → análise → filtro → ficha → relação | Etapa 3 + Claude |
| Tablet | Lista–detalhe e painel de apoio conforme o contexto | Claude + Matrix Dual |
| Planos | Comparar Objetivos, Projetos e Eventos como áreas relacionadas, sem apagar suas diferenças | Claude |
| Financeiro | Investigar “Comprometido” sem duplicar obrigações | Claude + relatório preparatório |
| Captura | Testar título primeiro e criação contextual, preservando campos avançados | Claude |
| Personalidade | Manter frases e identidade como parte do produto, sem ruído | Relatório preparatório |

## 8. Próximas validações necessárias

1. Conferir cada afirmação de funcionalidade atual no código e na documentação técnica vigente.
2. Mapear quais destinos e recursos são realmente usados com maior frequência.
3. Verificar se o usuário entende naturalmente as categorias Hoje, Planos e Mercado.
4. Testar a arquitetura em tarefas reais: planejar uma semana, preparar um evento, acompanhar uma fatura e localizar um registro antigo.
5. Prototipar no Stitch apenas as alternativas selecionadas, sem tratar o protótipo como implementação.
6. Comparar a experiência no celular, tablet retrato e tablet paisagem.
7. Registrar decisões humanas separadamente das hipóteses e manter ADRs/requisitos intactos até aprovação formal.

## 9. Status final desta síntese

- **Base de análise recomendada:** proposta do Claude.
- **Contribuições complementares:** profundidade progressiva, conexões vivas, Constellation e Matrix Dual.
- **Referência estratégica:** relatório preparatório, com revalidação técnica obrigatória.
- **Arquitetura aprovada:** nenhuma.
- **Implementação autorizada:** nenhuma.
- **Estado:** EM ANÁLISE.

A força desta rodada está em abrir possibilidades melhores, não em trocar imediatamente a arquitetura atual.