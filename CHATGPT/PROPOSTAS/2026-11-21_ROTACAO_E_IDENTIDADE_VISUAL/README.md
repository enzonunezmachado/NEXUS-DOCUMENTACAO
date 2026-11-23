# Pacote de trabalho — rotação e identidade visual NEXUS

**Data de origem:** 21/11/2026  
**Estado atual:** a exploração visual isolada chegou à segunda iteração (v2), conforme relato do usuário. O N-1 foi escolhido para a continuidade visual. O usuário prefere a família Grafite Suave, ainda em refinamento; também foram capturados critérios para contornos e layouts adaptativos. Nada disso foi implementado em produção.

## Conteúdo

- [Prompt integral original para Google AI Studio](PROMPT_GOOGLE_AI_STUDIO.md) — registro da solicitação inicial, não deve ser tratado como instrução atualizada para a próxima iteração.
- [Direção de identidade tipográfica NEXUS](DIRECAO_IDENTIDADE_TIPOGRAFICA.md)
- [Direção em análise — refinamento do tema claro](DIRECAO_TEMA_CLARO.md)
- [Direção em análise — layouts adaptativos](DIRECAO_LAYOUTS_ADAPTATIVOS.md)
- [Pesquisa inicial — layouts adaptativos e rolagem](PESQUISA_LAYOUTS_ADAPTATIVOS_E_ROLAGEM.md) — fundamentos oficiais Android, padrões de painéis e limites de rolagem no Compose.

## Escopo e evolução

1. Diagnóstico do BUG-001 e exploração inicial de identidade visual foram solicitados separadamente, sem autorização de alteração definitiva em produção.
2. A segunda iteração visual foi reportada como criada em `design-explorations/nexus_identidade_tipografica_2026_11_21_v2.html`, preservando a v1.
3. A v2 apresenta três construções de N; após a avaliação, o usuário definiu o N-1 (Monólito Chanfrado) como o N desejado. E/X/U/S da Kinetik Cut devem ser preservados.
4. O usuário gostou da direção Grafite Suave, mas pediu ajuste para uma tonalidade um pouco mais cinza/confortável e contornos mais espessos, usando como referência o destaque verde do dia selecionado na Agenda. Detalhes em [DIRECAO_TEMA_CLARO.md](DIRECAO_TEMA_CLARO.md).
5. O usuário solicitou que a Agenda semanal aproveite melhor a altura disponível e que tablets deitados/telas maiores recebam layouts adaptativos, incluindo Agenda em painel duplo. Detalhes em [DIRECAO_LAYOUTS_ADAPTATIVOS.md](DIRECAO_LAYOUTS_ADAPTATIVOS.md).

## Limites

- Os protótipos visuais permanecem isolados.
- O N-1 é a direção escolhida pelo usuário para a continuidade da exploração; isso não autoriza alterar o ícone Android de produção.
- Grafite Suave é a família preferida até aqui, mas a paleta ainda precisa de refinamento e aprovação final.
- Espessura final dos contornos e proporções dos layouts ainda não foram definidas.
- Não alterar código Android de produção, recursos do launcher, tema, fontes ou documentação técnica do aplicativo nesta etapa.
- BUG-001 não deve ser marcado como corrigido sem implementação e evidência.

## Próximo passo

Preparar a próxima iteração visual isolada consolidando o N-1, refinando Grafite Suave (um pouco mais cinza/confortável), ajustando contornos e demonstrando Agenda semanal com altura proporcional e layouts adaptativos para tablets deitados/telas maiores. Preservar v1/v2 e aguardar avaliação antes de qualquer mudança de produção.
