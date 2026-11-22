# Direção em análise — refinamento do tema claro

**Registro:** 22/11/2026  
**Tipo:** observação de UX e proposta de exploração visual  
**Estado:** capturada / em análise  
**Origem:** revisão da segunda iteração visual do NEXUS  
**Protótipo de referência:** [nexus_identidade_tipografica_2026_11_21_v2.html](https://github.com/enzonunezmachado/PROJETO-NEXUS-/blob/main/design-explorations/nexus_identidade_tipografica_2026_11_21_v2.html)

## 1. Observação que motivou o registro

Durante a avaliação da segunda iteração visual, o usuário apontou que o tema claro continua branco demais e excessivamente luminoso. A questão não é apenas reduzir o branco do fundo: é necessário avaliar a hierarquia visual entre fundo, superfícies, cartões, campos, barras e elementos secundários.

A inspeção do CSS do protótipo v2 identificou esta distribuição:

| Elemento | Cor usada no protótipo |
|---|---|
| Fundo principal da prévia | `#F6F6F6` |
| Barra superior | `#FFFFFF` |
| Barra de status | `#FFFFFF` |
| Cartões comuns | `#FFFFFF` |
| Cartões elevados | `#EDEDED` |

A predominância de branco puro nas superfícies pode explicar a sensação relatada. Essa observação se refere ao protótipo isolado, não constitui auditoria completa do tema de produção.

## 2. Direção a investigar

Explorar um tema claro mais confortável, com superfícies neutras em camadas e diferenças tonais perceptíveis, porém discretas. O objetivo é criar profundidade e hierarquia sem depender de sombras fortes, bordas excessivas ou cores decorativas.

Três famílias conceituais podem ser comparadas:

- **Mineral neutro:** cinzas discretos, aparência técnica e sóbria.
- **Off-white quente:** neutros levemente aquecidos, aparência acolhedora sem ficar amarelada.
- **Grafite suave:** cinzas mais frios, aparência tecnológica e contemporânea.

Essas famílias são alternativas para prototipagem, não paletas aprovadas nem valores finais de design tokens.

## 3. Elementos que devem ser avaliados

Aplicar as alternativas, no mínimo, às prévias de:

- Home;
- Agenda;
- Financeiro.

Comparar fundo geral, cartões e painéis, campos de entrada, barras superior e inferior, divisores, estados selecionados, textos primários e secundários, ícones e alertas.

Critérios de avaliação:

- conforto visual e redução da sensação de branco excessivo;
- hierarquia clara entre níveis de superfície;
- legibilidade e contraste suficiente;
- consistência entre componentes e telas;
- preservação dos significados semânticos dos alertas;
- coerência com o tema escuro, sem transformar o claro em uma versão desbotada dele.

Vermelho para atrasos e amarelo para conflitos devem manter seus significados. Cor não deve ser o único meio de comunicar um estado.

## 4. Limites e estado de decisão

- Esta observação não aprova uma paleta específica.
- As três famílias são propostas para comparação, não decisões.
- Não há autorização para alterar o tema de produção, recursos Android, fontes globais ou componentes reais.
- A próxima atividade deve permanecer em protótipo visual isolado.
- Não registrar a mudança como implementada ou validada.
- A escolha final depende da avaliação comparativa do usuário.

## 5. Relação com o catálogo estratégico

Esta direção detalha a ideia existente **IDEA-007 — Temas visuais harmonizados**. Não cria uma ideia independente nem duplica o catálogo central. O catálogo e o quadro de pendências apontam para este registro como detalhamento específico do tema claro.
