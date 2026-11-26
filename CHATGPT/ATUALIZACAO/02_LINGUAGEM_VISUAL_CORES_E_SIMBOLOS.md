# 02 — Linguagem visual, cores e símbolos

**Estado:** EM ANÁLISE  
**Escopo:** identidade visual, expressão por domínio, semântica de cor, ícones, contornos e consistência entre contextos.

## 1. Identidade visual

A wordmark atual “NEXUS” deve ser preservada como ponto de partida: o usuário manifestou satisfação explícita com sua aparência e não solicitou redesenho. O símbolo isolado usado como ícone/capa do aplicativo é uma questão separada: o “N” simples atual não agradou e deve ser reavaliado por exploração visual, sem alterar a wordmark automaticamente.

Direção capturada do usuário para a exploração:
- Manter a base de símbolo branco sobre fundo preto como referência principal.
- Explorar um símbolo proprietário que comunique conexão, convergência, continuidade, organização ou um ponto confiável de encontro das áreas da vida.
- Não escolher antecipadamente um conceito ou desenho; comparar alternativas em contexto real de ícone Android, splash/abertura e marca.
- Preservar o caráter reconhecível do NEXUS; não transformar a marca em um produto genérico nem copiar outra identidade.


A base do NEXUS permanece sóbria — preto, branco e grafite —, mas isso não significa monocromia obrigatória, frieza ou ausência de personalidade.

A direção é combinar:
- Elegância e clareza da identidade atual.
- Protagonismo do conteúdo.
- Superfícies e cartões bem compostos.
- Cantos e bordas proporcionais à escala do componente.
- Cores e degradês usados com intenção.
- Capas e imagens quando agregam contexto.
- Microinterações discretas e feedback visual coerente.

A inspiração aproximada de 15% da essência do Luma deve ser interpretada como referência de qualidade de apresentação, não como cópia de paleta, linguagem social ou estrutura de telas.

## 2. Personalidade por domínio, dentro de um sistema comum

Cada ambiente pode expressar sua finalidade sem parecer outro aplicativo:
- Eventos e Planejamento podem valorizar capas, imagens e composição contextual.
- Tarefas precisam favorecer leitura rápida, densidade e execução.
- Agenda deve priorizar orientação temporal e reconhecimento imediato.
- Dinheiro pode combinar sobriedade com gráficos e degradês controlados.
- Mercado pode usar visualizações mais expressivas, inclusive acentos neon, se legibilidade e identidade forem preservadas.

A variação deve acontecer por composição, hierarquia e acentos contextuais, não por temas arbitrariamente independentes.

## 3. Composição, tipografia e alinhamento

A auditoria visual deve abranger não apenas cores e ícones, mas também a composição editorial da interface:
- Alinhamento consistente de títulos, subtítulos, ícones, ações e conteúdo dentro de cada padrão de tela.
- Hierarquia tipográfica clara entre título da tela, seção, nome do registro, metadados e texto auxiliar.
- Espaçamento e margens consistentes, respeitando exceções justificadas pelo contexto.
- Alinhamento entre telas equivalentes e componentes reutilizados, sem forçar posições idênticas quando a função for diferente.
- Verificar truncamento, quebras de linha, títulos longos e densidade em diferentes tamanhos de tela.

## 4. Proporção, superfícies e bordas

Não aumentar raios, espessuras ou contornos indiscriminadamente. A decisão deve considerar tamanho, função, densidade e proximidade entre componentes.

Um card amplo pode suportar raio e contorno mais perceptíveis; uma tarefa compacta ou um ícone pequeno pode precisar de tratamento discreto. A linguagem deve ser consistente sem ignorar a escala.

A auditoria deve incluir:
- Cards, linhas, chips, botões e campos.
- Ícones isolados e controles pequenos.
- Tema claro e escuro.
- Estados padrão, pressionado, selecionado, desabilitado e foco.
- Hierarquia de superfícies e separação entre grupos.

## 5. O conflito semântico que precisa ser resolvido

Há duas necessidades diferentes que podem competir pelo mesmo canal visual:

1. **Estado operacional:** atraso, conflito, atenção, conclusão, bloqueio ou prioridade.
2. **Identidade contextual/relação:** indicar a qual projeto, objetivo, evento ou contexto um registro está associado.

O NEXUS já possui semânticas de estado e regras documentadas no Design System, incluindo o princípio de priorizar forma e ícone antes da cor e ajustes para evitar agressividade visual excessiva em situações de atraso.

Essas regras não devem ser apagadas ou substituídas silenciosamente. Primeiro é necessário inventariar o uso real no código e compreender o significado de cada cor, contorno, ícone e papel visual.

## 6. Canais visuais possíveis — ainda sem decisão final

Uma hipótese é separar responsabilidades em canais distintos:

| Informação | Canal candidato | Observação |
|---|---|---|
| Tipo de registro | Ícone e forma | Deve ser reconhecível sem parecer ação indevida |
| Estado operacional | Cor semântica, texto, ícone ou contorno | Preservar e auditar as regras vigentes |
| Relação/contexto | Ponto, filete, faixa, chip ou acento discreto | Não competir com o status |
| Categoria | Cor ou símbolo de categoria | Conferir semântica já existente |
| Seleção/foco | Contorno, superfície ou contraste próprio | Não confundir com alerta |

A cor contextual de um projeto poderia ajudar a reconhecer tarefas relacionadas, mas isso é apenas uma alternativa. Outras soluções — nome curto, ícone, etiqueta, avatar ou símbolo — também devem ser comparadas.

Registros com múltiplos vínculos tornam a questão mais complexa: não presumir uma única cor herdada nem escolher arbitrariamente um vínculo principal.

**Nenhuma paleta ou regra de herança de cor está aprovada nesta fase.**

## 7. Intensidade e significado dos estados

A auditoria deve distinguir atraso, conflito, bloqueio, prioridade, conclusão, seleção e atenção. Não usar vermelho, amarelo ou qualquer outra cor como sinônimos intercambiáveis.

Também deve verificar:
- Se o estado é compreensível por forma, ícone e texto.
- Se a intensidade visual é proporcional à gravidade.
- Se o mesmo significado aparece de modo coerente em Hoje, Agenda, Tarefas, Eventos e Dinheiro.
- Se tema claro e escuro preservam contraste e significado.
- Se componentes pequenos continuam legíveis.

## 8. Inventário e remapeamento de ícones

Foi identificada uma inconsistência de expectativa: a tarefa aparece com um controle circular de conclusão na área Tarefas, enquanto em alguns contextos da Agenda aparece um quadrado que pode ser entendido como checkbox ou como marcador de tipo.

A auditoria deve separar:
- **Ícone de tipo:** identifica que o registro é uma tarefa, evento, compromisso ou lançamento.
- **Controle de ação:** executa uma ação, como concluir uma tarefa.

Um símbolo que parece checkbox deve efetivamente funcionar como controle de conclusão. Um ícone informativo deve ser visualmente distinto e não sugerir uma ação inexistente.

Fazer inventário transversal de símbolos na Agenda e nos demais módulos, sem substituir ícones existentes antes de entender suas funções. Na Agenda, revisar com prioridade os símbolos atuais de tarefa e compromisso que podem ser confundidos com checkbox, seleção ou mero traço.

A família de ícones deve definir peso de traço, proporção, preenchimento, geometria e comportamento em tamanhos pequenos. Separar visualmente o símbolo que identifica o tipo de registro do controle que executa a ação de concluir. Se concluir tarefa pela Agenda for permitido, o controle deve ser reconhecível como ação e ter área de toque adequada; o ícone de tipo não deve simular essa ação.

## 9. Acessibilidade

Nenhuma informação essencial deve depender exclusivamente da cor. Combinar, quando necessário, cor com forma, texto, ícone, padrão ou descrição acessível.

Critérios:
- Contraste em temas claro e escuro.
- Legibilidade em tamanhos pequenos.
- Daltonismo e distinção sem cor.
- Áreas de toque adequadas.
- Compatibilidade com recursos de acessibilidade.
- Respeito à preferência de movimento reduzido.

## 10. Questões abertas

- Como representar relação sem competir com status?
- Como lidar com registros de múltiplos vínculos?
- Cor contextual, símbolo, chip ou combinação?
- Quais cores e contornos atuais têm significado normativo?
- Quais ícones da Agenda precisam ser remapeados?
- Como manter identidade entre domínios sem uniformizar suas composições?
