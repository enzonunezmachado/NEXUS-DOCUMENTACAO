# 04 — Interações, relações e navegação

**Estado:** EM ANÁLISE  
**Objetivo:** tornar inequívoco o que acontece ao tocar, concluir, consultar, expandir, editar ou navegar.

## 1. Princípio: uma aparência, uma expectativa coerente

Nem tudo precisa ser clicável. Elementos informativos devem parecer informativos; controles devem parecer controles; destinos devem indicar navegação.

Não criar affordances falsas. Se um elemento parece checkbox, deve executar conclusão; se parece lápis, deve editar; se parece link, deve levar ao destino anunciado.

## 2. Ações diferentes não devem ser confundidas

| Intenção | Comportamento esperado |
|---|---|
| Concluir | Alterar o estado da tarefa, respeitando regras e permissões |
| Consultar | Abrir a ficha própria da entidade |
| Expandir/recolher | Revelar ou ocultar detalhes no contexto atual |
| Editar | Abrir formulário ou modo de edição específico |
| Navegar | Ir à área ou ficha canônica de outro registro |
| Excluir | Executar fluxo próprio, com confirmação quando necessária |
| Ver todas | Abrir a coleção ou visão completa |

A área clicável de cada ação deve ser clara. Não fazer com que um toque no checkbox abra a ficha, nem que tocar no título conclua acidentalmente a tarefa.

## 3. Contrato de tarefa em múltiplos contextos

A mesma tarefa pode aparecer em Hoje, Agenda, Tarefas, Evento ou Projeto. Sua identidade e estado devem permanecer coerentes.

Direções:
- Checkbox/controle de conclusão deve concluir ou alternar o estado, quando permitido.
- Ícone de tipo apenas identifica que o registro é uma tarefa e não deve parecer checkbox.
- Tocar no título ou conteúdo principal abre a ficha da tarefa.
- Editar é uma ação explícita e separada.
- Ações equivalentes devem se comportar de modo equivalente nos contextos, salvo restrições justificadas.

É necessário auditar o comportamento real de cada ocorrência, inclusive tarefas recorrentes, bloqueadas ou que exigem confirmação. Não prometer conclusão direta quando as regras não permitem.

## 4. Relações contextuais

Mostrar relações quando ajudam a compreender, decidir ou agir. Não despejar em todas as fichas a cadeia completa de relações transitivas.

Exemplo de evento:
- Tarefas relacionadas podem ser relevantes e aparecer como amostra.
- Projeto ou objetivo associado pode aparecer se o vínculo existir e for útil.
- Acesso ao projeto/objetivo leva à ficha canônica correspondente.

Exemplo de tarefa:
- Mostrar o contexto imediato mais útil.
- Não exibir automaticamente projeto → objetivo → evento e todos os vínculos indiretos.
- Oferecer acesso aos detalhes quando necessário.

Uma relação não deve ser inferida apenas pela proximidade visual. A apresentação precisa refletir o vínculo real existente nos dados.

## 5. Navegação entre entidades

Ao tocar numa relação, navegar para a tela ou ficha canônica do destino, com retorno previsível. Evitar abrir fichas indefinidamente em camadas sobrepostas.

A experiência deve preservar contexto quando fizer sentido:
- Ao voltar, recuperar posição, filtros ou estado relevantes.
- Não manter uma pilha visual infinita de cards.
- Diferenciar expansão local de navegação para outra entidade.
- Respeitar o padrão de navegação e estado já existente até que uma mudança seja analisada e aprovada.

A correção de problemas reais de navegação e preservação de estado deve ser tratada com evidência do app, não presumida a partir de um protótipo.

## 6. Microinterações e feedback

Evoluções possíveis:
- Expansão e recolhimento suaves.
- Deslocamento natural do conteúdo abaixo.
- Feedback visual e tátil de ações.
- Confirmação visual de conclusão.
- Transições entre contextos.
- Progresso animado com moderação.

Esses refinamentos vêm depois de definir semântica, comportamento, desempenho e acessibilidade. Movimento deve reforçar a compreensão, nunca atrasar ações. Respeitar configurações de movimento reduzido.

## 7. Questões abertas

- Em quais contextos cada tarefa pode ser concluída diretamente?
- Como tratar recorrências, bloqueios, confirmações e estados especiais?
- Qual contexto precisa ser preservado ao navegar e retornar?
- Quais relações são relevantes em cada ficha?
- Quais cards realmente se beneficiam de expansão?
- Como comunicar ações sem aumentar ruído visual ou área clicável acidental?
