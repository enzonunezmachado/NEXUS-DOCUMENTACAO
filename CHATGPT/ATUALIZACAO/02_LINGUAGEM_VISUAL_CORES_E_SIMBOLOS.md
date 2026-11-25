# 02 — Linguagem visual, cores e símbolos

**Estado:** EM ANÁLISE  
**Escopo:** identidade visual transversal, semântica de cor, ícones, contornos e remapeamento da Agenda.

## 1. Direção de identidade

O NEXUS deve manter uma base sóbria em preto, branco e grafite, mas não ser monocromático por princípio. Cada ambiente pode ter personalidade visual adequada à sua função, desde que todos pertençam à mesma linguagem de produto.

Exemplos de expressão a explorar:
- Agenda: sinais temporais e tipos de registro facilmente reconhecíveis.
- Planejamento: capas, imagens, acentos e composições mais expressivas quando agregarem contexto.
- Tarefas: identificação compacta e baixa carga visual.
- Dinheiro: gráficos e degradês controlados.
- Mercado: visual tecnológico e gráficos com acentos mais expressivos.

## 2. Sistema de informação visual — problema central

A conversa identificou uma possível colisão entre dois usos de cor:
1. Cor para comunicar estado operacional (atraso, conflito, conclusão, atenção etc.).
2. Cor para comunicar contexto ou relacionamento (por exemplo, identificar que uma tarefa pertence a determinado projeto).

O NEXUS já utiliza cores semânticas e contornos para alguns estados. A regra documentada no Design System vigente é “forma e ícone antes da cor”, com cores semânticas e papéis de contorno. Também houve ajuste anterior para reduzir a agressividade visual do vermelho em situações de atraso.

**Não substituir nem enfraquecer silenciosamente as semânticas existentes.** Antes de decidir uma nova camada, auditar o uso real de cores, estados, `ColoredBorderRole` e componentes no código.

## 3. Hipótese para separar responsabilidades

Uma possibilidade a avaliar é manter canais distintos:

| Informação | Canal visual possível | Estado |
|---|---|---|
| Tipo de registro | Ícone e forma | Princípio vigente a preservar e auditar |
| Estado operacional | Cor semântica, contorno, texto ou símbolo | Semântica existente a preservar até revisão |
| Relacionamento/contexto | Ponto, faixa lateral, marcador, chip ou acento discreto | Hipótese nova |
| Categoria | Cor de categoria, quando aplicável | Conferir regras atuais |
| Destaque/seleção | Superfície, contraste ou contorno próprio | Auditar no sistema atual |

A hipótese é que a cor de relacionamento não substitua a cor de estado. Uma tarefa poderia, por exemplo, ter um marcador contextual discreto associado ao projeto e continuar comunicando atraso ou conflito pelo mecanismo semântico atual.

Isso ainda não é decisão. A representação exata precisa ser prototipada e testada em tamanhos pequenos, listas densas, Agenda e fichas.

## 4. Cores de estado e intensidade

A experiência anterior do NEXUS já identificou que vermelho excessivamente intenso pode produzir sensação de perigo/agressividade. O tratamento de atraso foi suavizado. Essa intenção deve ser preservada na auditoria.

É necessário verificar:
- Quais estados têm cor atualmente.
- Quais estados usam contorno, fundo, texto ou ícone.
- Diferenças entre atraso, conflito, bloqueio, prioridade, conclusão e seleção.
- Como a semântica aparece em tema claro e escuro.
- Como cores e símbolos funcionam em componentes pequenos.
- Se a mesma semântica é aplicada de forma coerente em Agenda, Tarefas, Home, Eventos e Financeiro.

Não atribuir cores finais nesta fase.

## 5. Remapeamento de ícones e símbolos

Foi identificada uma inconsistência perceptível: na aba Tarefas, a ação de concluir é representada por um círculo/checkbox; na Agenda, tarefas podem aparecer com um símbolo quadrado como marcador de tipo. O usuário não compreendeu a diferença.

Precisamos distinguir duas funções que podem ter sido confundidas:
- **Ícone de tipo:** informa que o item é uma tarefa.
- **Controle de ação:** permite concluir a tarefa.

Eles não precisam ser o mesmo componente, mas devem ser semanticamente claros e consistentes. Um marcador de tipo não deve parecer um checkbox funcional.

A Agenda precisa de auditoria e remapeamento visual completo dos símbolos de tarefa, compromisso, evento, projeto, financeiro e demais ocorrências. O objetivo é que cada tipo seja reconhecível, consistente com o restante do app e não confunda identificação com ação.

## 6. Regra de acessibilidade

Nenhum estado ou vínculo deve depender exclusivamente da cor. Sempre que necessário, combinar cor com forma, ícone, texto, padrão ou descrição acessível. Contraste, tamanho mínimo, daltonismo e movimento reduzido devem entrar nos critérios de validação.

## 7. Decisões em aberto

- Manter integralmente a semântica de estado atual ou revisá-la após auditoria?
- Qual canal representa relacionamento sem competir com status?
- Uma entidade com múltiplos vínculos pode ter mais de um marcador contextual?
- Como definir prioridade se uma tarefa se relaciona a projeto, objetivo e evento?
- Quais ícones representam tipos de ocorrência na Agenda?
- Como preservar a identidade de cores em tema claro e escuro?
