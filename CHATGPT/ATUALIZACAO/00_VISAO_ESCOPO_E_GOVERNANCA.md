# 00 — Visão, escopo e governança da reformulação

**Estado:** EM ANÁLISE  
**Origem:** conversas de 24–25/11/2026 e rodada de arquitetura de 24/11/2026.  
**Natureza:** consolidação estratégica de propostas e requisitos desejados.

## 1. Intenção central

O NEXUS deve evoluir de uma experiência percebida em alguns pontos como um conjunto de listas, formulários e caixas administrativas para um aplicativo pessoal mais refinado, agradável, fluido, expressivo e funcional.

A reformulação deve atingir a forma como o sistema inteiro é apresentado ao usuário: organização das áreas, hierarquia visual, cards, fichas, ícones, cores, relações, navegação, ações, animações e adaptação a diferentes telas.

Não se trata de decorar telas existentes nem de criar uma “capa bonita” sobre planilhas. A apresentação precisa revelar a natureza de cada registro e tornar as relações e ações compreensíveis.

## 2. Personalidade pretendida

Metáfora de direção do usuário: **“um pouco da essência do Luma — aproximadamente 15% — reinterpretada pela personalidade do NEXUS.”**

O número é uma metáfora, não uma métrica de design.

A referência Luma contribui como inspiração para:
- Conteúdo com protagonismo e personalidade.
- Capas e imagens quando acrescentam contexto.
- Cards com cantos arredondados e composição bem resolvida.
- Hierarquia visual clara e agradável.
- Uso intencional de cores.
- Sensação de produto vivo e convidativo, sem depender de telas excessivamente cheias.

O NEXUS deve manter identidade própria:
- Base sóbria em preto, branco e grafite.
- Aparência tecnológica, elegante e organizada.
- Cores adicionais usadas com intenção semântica ou contextual.
- Ambientes com personalidade adequada à finalidade, mas pertencentes ao mesmo sistema visual.
- Sofisticação sem frieza; expressividade sem ruído.

Não copiar a interface, paleta ou linguagem social do Luma. A inspiração está nos princípios de composição e apresentação, não na reprodução literal.

## 3. O que significa “reconstrução”

Neste contexto, reconstrução significa reavaliar amplamente a experiência e a apresentação das telas. Não significa autorização para:
- Reescrever o aplicativo do zero.
- Substituir Kotlin, Jetpack Compose, Room ou a arquitetura vigente.
- Alterar o modelo de dados sem análise.
- Remover funcionalidades ou simplificar regras de negócio.
- Trocar navegação por estado por outra solução sem decisão formal.

A estratégia preferida é investigar, desenhar e planejar uma evolução coerente, aproveitando o que funciona e identificando o que realmente precisa mudar.

## 4. Princípios de experiência desejados

1. **Conteúdo antes de estrutura vazia:** a tela deve se adaptar à quantidade e relevância dos dados.
2. **Densidade com intenção:** nem tudo precisa ser um card grande; listas compactas continuam úteis quando há muitos registros.
3. **Identidade por entidade:** evento, projeto, objetivo, tarefa e lançamento financeiro não devem parecer a mesma ficha com campos trocados.
4. **Profundidade progressiva:** primeiro uma leitura clara; detalhes e controles avançados aparecem quando necessários.
5. **Relações compreensíveis:** mostrar vínculos quando ajudam a entender ou agir, sem despejar a árvore inteira de relações.
6. **Interação previsível:** aparência clicável deve corresponder a uma ação real e coerente.
7. **Consistência sem uniformidade artificial:** componentes compartilham regras, mas cada domínio pode ter composição adequada.
8. **Acessibilidade:** não depender exclusivamente de cor; preservar contraste, legibilidade, áreas de toque e opção de movimento reduzido.
9. **Integridade funcional:** melhorias visuais não podem quebrar regras, cálculos, persistência, histórico ou navegação.
10. **Adaptabilidade:** celular e tablet, retrato e paisagem, com reorganização contextual e não simples esticamento.

## 5. Limites e fontes de verdade

A implementação atual é determinada pelo código do repositório ativo `PROJETO-NEXUS-`. A documentação técnica vigente orienta os contratos e requisitos. Esta pasta pertence à memória estratégica e não substitui essas fontes.

A proposta Claude de arquitetura da informação é uma hipótese estrutural prioritária para avaliação, conforme a comparação crítica da rodada de 24/11/2026. Não deve ser adotada inteira automaticamente.

## 6. Vocabulário de estado

Usar os estados documentais vigentes do projeto: CAPTURADA, EM PESQUISA, EM ANÁLISE, PROPOSTA, APROVADA, PLANEJADA, EM IMPLEMENTAÇÃO, IMPLEMENTADA, PAUSADA, REJEITADA, DESCARTADA e SUBSTITUÍDA.

No momento, esta iniciativa permanece **EM ANÁLISE**. Ideias específicas podem estar capturadas ou propostas, mas nenhuma arquitetura visual consolidada foi aprovada nesta rodada.
