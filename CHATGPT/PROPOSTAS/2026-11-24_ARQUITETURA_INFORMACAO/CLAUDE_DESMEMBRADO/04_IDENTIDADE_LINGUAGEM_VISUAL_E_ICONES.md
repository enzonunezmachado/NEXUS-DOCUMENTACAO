# Claude — Identidade, linguagem visual e ícones

> **Origem:** extração temática fiel da proposta completa do Claude, preservada sem reescrita substantiva.  
> **Estado:** PROPOSTA / EM ANÁLISE. Este material não representa decisão aprovada nem autorização de implementação.  
> **Fonte integral preservada:** `NEXUS — Proposta de Arquitetura da Informação e Experiência (2).md`. Consulte a fonte original para contexto contínuo e referências.

---

## G. Linguagem visual

### G.1 O que é fundação e o que é refinamento

Você pediu para separar o que precisa existir antes do que pode vir depois. Minha ordem \[proposta\]:

| Camada | Conteúdo | Por que nesta ordem |
| --- | --- | --- |
| **1. Fundação** | Quatro canais de significado (tipo, estado, vínculo, ação); hierarquia tipográfica da página (título de seção acima do título de cartão); escada de formatos (linha → cartão → ficha); regras de densidade; auditoria de cores e ícones | Sem isso, qualquer capa ou degradê entra em cima de ambiguidades |
| **2. Componentes** | Raios e superfícies proporcionais à escala; cartões por entidade; estados vazios intencionais | Dá a forma de cada tipo |
| **3. Expressão** | Capas e imagens (exigem dado novo), degradês controlados, acentos por domínio, neon no Mercado | Só faz sentido com 1 e 2 prontos |
| **4. Movimento** | Expansão, transições, feedback tátil, animações com moderação | Depende de semântica estável e de movimento reduzido respeitado |

Os “15% do Luma” \[intenção\] moram sobretudo nas camadas 2 e 3 — composição, protagonismo do conteúdo, capas, cor intencional. **Não são o ponto de partida.**

### G.2 Quatro canais, uma responsabilidade cada

| Canal | Pergunta que responde | Forma preferida | Cor |
| --- | --- | --- | --- |
| **Tipo** | O que é isto? | Ícone e forma, em neutro | Não usa cor |
| **Estado** | Em que condição está? | Texto curto + ícone + cor semântica proporcional à gravidade | Vermelho, âmbar, verde, azul, **só para estado** |
| **Vínculo** | A que está ligado? | Chip com ícone do tipo + nome curto | Neutra; acento de identidade só em superfícies grandes |
| **Ação** | O que acontece se eu tocar? | Controle com forma de controle, área ≥ 48 dp, rótulo acessível | Realce neutro |

A **categoria** (já existente, com cor escolhida pelo usuário) é um quinto elemento: marca discreta (ponto ou filete) e cor nos gráficos \[código\]\[doc\].

### G.3 O que as cores já significam hoje \[código\]\[doc\]

| Token | Significado documentado | Usos adicionais observados | Observação |
| --- | --- | --- | --- |
| Âmbar (warning) | Atenção, conflito, orçamento 80–100%, dado desatualizado | Prioridade **alta** (ponto), ingresso **Pendente**, “Próximo venc.”, **todos** os itens financeiros da Agenda | Pelo menos cinco significados |
| Vermelho (danger) | Atrasado, excedido, queda, exclusão | Prioridade **urgente** (ponto) | Coerente, mas mistura estado e prioridade |
| Azul (info) | Em andamento, previsto, informativo | **Cor dos links** (“Ver todas”, “Finanças”…) | Link e estado compartilham o canal |
| Verde (positive) | Concluído, pago, receita realizada, alta | — | — |
| Verde-menta (accentNeon) | Anel concluído, receitas, seleção | Barras de meta financeira, ícone de recorrência | Dois verdes com papéis diferentes |

Isso confirma a sua preocupação: **o conflito não é hipotético; já existe**. Antes de qualquer cor de vínculo, é preciso decidir se estados e prioridades vão continuar dividindo o âmbar. Sugestões, **todas dependentes de aprovação porque alteram semânticas existentes** \[proposta\]: prioridade passa a usar forma ou ícone (e não ponto âmbar ou vermelho solto); itens financeiros da Agenda ganham cor pelo estado real (vencida, aberta, paga) e não âmbar fixo; links deixam o azul de estado e usam realce neutro com seta.

### G.4 Cor de identidade para vínculos: reavaliação

A ideia “o projeto tem uma cor e as tarefas mostram um indicador dela” é sedutora e **eu não a recomendo como mecanismo principal**:

- A paleta categórica de gráficos já inclui âmbar, rosa-avermelhado e verde, que **colidem** com os estados.
- **Já existe uma cor por categoria**, escolhida pelo usuário; seria uma segunda cor por linha.
- A tarefa pode ter **três** contextos (D.2): qual cor herdaria?
- Um ponto de 6 a 8 dp não distingue oito projetos, e daltônicos distinguem ainda menos.

| Alternativa | Ajuda em | Problema | Veredito |
| --- | --- | --- | --- |
| Cor herdada do projeto | Reconhecimento rápido de poucos projetos | Colide com estados; ruído com múltiplos vínculos; acessibilidade | Não como mecanismo principal |
| Filete lateral | Agrupamento visual numa lista | Já é usado como marca do tipo “compromisso” na Agenda; só um por linha | Reservar ao tipo |
| Ponto pequeno | Discreto | Ambíguo, parece estado ou prioridade | Evitar |
| Ícone do tipo de vínculo | Diz *qual* tipo de contexto (projeto, evento, objetivo) | Não diz *qual* projeto | Bom, **combinado** |
| **Chip com ícone e nome curto** | Diz tipo e identidade, sem depender de cor | Ocupa largura | **Principal** |
| Monograma ou avatar | Identidade em espaço pequeno | Pede cadastro e convenções | Futuro, só se houver necessidade |
| Acento de identidade em **superfícies grandes** | Reconhecimento de capa e cabeçalho | Exige paleta própria | Sim, de uma **paleta separada** |

**Direção proposta \[proposta, a testar\]:** vínculo = **chip com ícone e nome**; vínculo redundante é omitido (dentro do projeto, o chip do projeto não aparece); com vários vínculos, mostra-se o **mais útil ao contexto** mais “+1” (na lista de Tarefas, o projeto; na Agenda, a origem do item; na Início, o que explica a urgência), e a ficha mostra todos; cor de identidade só em capa, cabeçalho e barra de progresso, tirada de uma **paleta de identidade separada**, de tons dessaturados que não se parecem com vermelho, âmbar, verde nem azul semânticos (ardósia, violeta, petróleo, areia). A categoria segue como ponto discreto onde já existe.

### G.5 Auditoria de ícones: o que o código mostra \[código\]

| Registro | Hoje | Problema | Direção \[proposta\] |
| --- | --- | --- | --- |
| Tarefa (lista) | Círculo vazio ou preenchido, **controle** de conclusão | Bom, mas sem ícone de tipo | Manter o círculo como controle |
| Tarefa (Agenda) | **Quadrado** de 22 dp com contorno, vazio ou marcado, **sem ação** | Parece checkbox e não é; o Design System o chama de checkbox | Ou vira controle real (conforme a regra de conclusão, I.2), ou ganha ícone de tipo sem forma de caixa |
| Compromisso | Barra vertical colorida | Reserva o “filete” para o tipo | Manter e **não** usar filete para vínculo |
| Evento | Ícone de **ingresso** em emblema | Evento gratuito ou sem ingresso; confunde com estado do ingresso | Ícone neutro de evento; ingresso como estado à parte |
| Lançamento | Caixa com “R$” | Bom como tipo | Manter |
| Projeto | Bandeira | Bom como tipo | Manter |
| Recorrência | Ícone de repetição em verde-menta | Usa cor de acento para um atributo | Neutro |
| Estados | Ponto + texto “Atrasada”; marca de verificação | Bom | Preservar a regra de “forma e texto antes da cor” |

O inventário completo precisa percorrer todos os módulos no aparelho \[pendente\].

### G.6 Densidade, bordas e superfícies

O sistema atual já tem raios (cartão 16 dp; botões e campos 10–12 dp; chips 8–10 dp) e **contornos coloridos proporcionais** ao porte do componente \[doc\]. Mantenho a regra “o raio e o contorno acompanham a escala” e proponho uma **escada de formatos** (detalhada em H): linha (sem cartão, só divisor) → cartão compacto → cartão expressivo → ficha. Não proponho números novos sem auditoria; a regra é **não aumentar bordas indiscriminadamente**, como diz a sua documentação.

### G.7 Personalidade por domínio, dentro de um sistema só

| Domínio | Papel visual | Expressão permitida | Limite |
| --- | --- | --- | --- |
| Tarefas | Leitura e execução | Linhas compactas e chips; sem imagem | Densidade antes de decoração |
| Agenda | Orientação temporal | Cartões de dia (referência existente), blocos de tempo | Cor só para estado |
| Eventos | Ocasião | Capa opcional, composição, local e data em destaque | **Sem imagem, composição intencional** (gradiente neutro com inicial ou ícone), nunca placeholder chamativo |
| Projetos | Propósito e progresso | Cabeçalho com acento de identidade, barra de progresso | — |
| Objetivos | Horizonte | Linha do tempo de marcos | — |
| Dinheiro | Sobriedade | Gráficos com degradê controlado, numerais tabulares | Valores legíveis em primeiro lugar |
| Mercado | Ambiente externo | Acentos neon, histórico e sparkline mais expressivos | Variação distinguível sem cor (seta e sinal) |
| Frases | Voz | Tipografia | Só em momentos calmos; nunca em alertas |

### G.8 Acessibilidade

Informação essencial nunca só por cor: estado = texto + ícone + cor. Contraste nos dois temas já tem tokens próprios (escuro e Grafite Suave) \[doc\]. Área de toque: o círculo de conclusão é desenhado com 32 dp \[código\]; a área efetiva precisa ser conferida para chegar a 48 dp \[pendente\]. Movimento reduzido deve valer para qualquer animação nova. Descrições acessíveis já existem no círculo de conclusão \[código\]; precisam existir também nos chips e nas relações.

## H. Componentes e fichas

