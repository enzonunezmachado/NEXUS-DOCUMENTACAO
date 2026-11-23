## 2026-11-23 — Arquivamento de artefatos temporários de transferência entre IAs

- O usuário esclareceu que atua como coordenador operacional da continuidade entre ChatGPT, Google AI Studio, GitHub e documentação: transporta prompts, relatórios, arquivos Markdown e instruções de retomada entre ambientes, além de acompanhar decisões e testes. A metodologia deve reconhecer esse trabalho de orquestração, não reduzir sua participação a sincronizar o repositório e instalar APKs.
- Foram localizados na raiz do repositório ativo `PROJETO-NEXUS-` dois arquivos temporários usados na passagem de contexto: `OlhaaquiGoogle aStudio.md` (prompt de implementação visual/adaptativa) e `Veja aqui chat gpt` (relatório de retorno para análise).
- Os conteúdos integrais foram copiados para `ARQUIVO/ARTEFATOS_DE_TRANSFERENCIA/2026-11-22_LOTE_VISUAL_ADAPTATIVO/` neste repositório documental, com nomes descritivos e README explicando sua natureza histórica.
- Os arquivos arquivados foram relidos e comparados com os originais; ambos apresentaram conteúdo idêntico antes da remoção.
- Após a verificação, os dois arquivos temporários foram removidos da raiz do repositório ativo. Nenhum código Android, documentação técnica vigente em `docs/`, ADR ou artefato interno do AI Studio foi alterado por essa limpeza.
- A raiz de `PROJETO-NEXUS-` fica livre desses arquivos de passagem; os registros continuam recuperáveis no arquivo histórico do repositório NEXUS-DOCUMENTACAO.
- Esta organização não estabelece uma pasta genérica de descarte: trata-se de arquivo histórico para artefatos operacionais de transferência entre ambientes.

## 2026-11-22 — BUG-001 persiste após o lote visual/adaptativo e rastreabilidade para ATLAS

- Após instalar/testar a versão com o lote visual/adaptativo, o usuário relatou que girar celular ou tablet ainda pode retornar à tela inicial em diferentes abas.
- Exemplo explícito: abrir a edição de uma tarefa já existente, girar o dispositivo e perder a tela de edição, voltando à Home.
- O BUG-001 permanece EM ABERTO. O lote visual/adaptativo não resolveu o problema funcional de preservação da rota e do estado de edição.
- Atualizado o registro detalhado em 01_PROBLEMAS_E_CORRECOES.md com a nova evidência e o estado correto.
- Reforçada a necessidade de investigar o padrão transversal de navegação e editores, não corrigir apenas uma tela isolada.
- Localizado no repositório ativo o arquivo docs/HISTORICO_DOCUMENTACAO.md. Ele foi ampliado para ser o registro central de todos os lotes relevantes de atualização documental em /docs/, com caminhos, motivação, evidências, limites e itens não alterados.
- Atualizados no repositório ativo docs/07_ESTADO_ATUAL.md e docs/12_RASTREABILIDADE.md para registrar persistência e critérios de validação do BUG-001.
- O estudo de caso NEXUS no ATLAS será atualizado como aprendizado metodológico contextual; não será alterado o changelog do ATLAS oficial nem criada regra universal a partir de um único caso.
- Nenhum código Android foi alterado; nenhum build, teste ou correção funcional foi executado nesta operação.
- Os artefatos internos do Google AI Studio não foram acessados nem atualizados.

# Histórico de registros da memória estratégica

Este arquivo registra consolidações realizadas nesta pasta. Não é um log integral de conversas. Alterações relevantes em CHATGPT devem ser registradas aqui, incluindo motivo, documentos afetados e limites.

## 2026-11-22 — Feedback real da Agenda semanal e pesquisa adaptativa

- Após instalar e testar a versão atual, o usuário gostou das mudanças em geral, mas considerou insatisfatória a composição da interface do tablet em orientação horizontal.
- O usuário aprovou a aparência atual dos cartões semanais no celular e também a aparência dos cartões do Kanban no celular. A crítica à Agenda é principalmente de densidade: as informações dos dias ficaram compactas demais.
- Nova direção para a visão semanal: o painel principal deve ocupar praticamente toda a altura útil, chegando próximo da barra inferior de navegação; a página semanal não deve rolar verticalmente como um todo.
- Cada cartão de dia deve possuir rolagem interna independente quando houver mais tarefas/compromissos do que cabem na área disponível.
- A linguagem visual dos cartões da Agenda deve alcançar a qualidade percebida nos cartões Kanban, compartilhando princípios de superfície, hierarquia, espaçamento e legibilidade, sem copiar mecanicamente a proporção horizontal ou a estrutura do Kanban.
- O usuário pediu pesquisa de como plataformas e aplicações adaptam interfaces para diferentes dimensões, orientações e proporções de tela, para orientar uma proposta melhor ao Google AI Studio.
- Pesquisa inicial em documentação oficial Android identificou window size classes por largura e altura, layouts canônicos de múltiplos painéis e orientações específicas sobre rolagem aninhada no Compose. Fontes e achados foram registrados em DIRECAO_LAYOUTS_ADAPTATIVOS.md.
- Criada metodologia explícita para separar ideias capturadas, pesquisa, análise, proposta, aprovação, planejamento, implementação e validação.
- Estado da IDEA-006 atualizado para em pesquisa / em análise. Não houve autorização para implementação nesta etapa; BUG-001 continua separado.

## 2026-11-22 — Grafite Suave, contornos e Agenda adaptativa

- O usuário gostou da direção Grafite Suave, mas pediu uma tonalidade um pouco mais cinza e confortável. A paleta apresentada foi registrada como referência inicial, não como tokens finais: fundo #D4D7DC, superfície #E3E5E8, elevado #C9CED4, borda #B0B6BF, texto #121417, atraso #AD1E1E, conflito #874E00, positivo #136B31 e informação #1353A6.
- O usuário observou que contornos de alertas e outros elementos estão finos demais. A espessura do contorno verde do dia selecionado na visão semanal da Agenda foi indicada como referência visual.
- Solicitou que os cartões semanais da Agenda tenham altura proporcional à área vertical disponível, considerando tamanho e orientação da tela.
- Expandida a IDEA-006: em tablet deitado, a Agenda deve poder apresentar calendário/dias de um lado e os itens do dia selecionado do outro. A direção também considera telas maiores, inclusive formato tipo monitor.
- A rotação permanece ligada ao BUG-001: além do layout adaptativo, é necessário preservar tela, dados e progresso ao recriar a Activity. Adaptação visual e preservação de estado são preocupações relacionadas, mas distintas.
- Criado [Direção de layouts adaptativos](PROPOSTAS/2026-11-21_ROTACAO_E_IDENTIDADE_VISUAL/DIRECAO_LAYOUTS_ADAPTATIVOS.md) e atualizados os índices e detalhes das IDEA-006/007.
- Estado: capturado / em análise. Nenhuma paleta, espessura de borda, breakpoint ou layout final foi aprovado; nenhuma implementação em produção foi autorizada.

## 2026-11-22 — Registro do refinamento do tema claro

- Após a revisão da segunda iteração visual isolada, registrada no repositório ativo como `design-explorations/nexus_identidade_tipografica_2026_11_21_v2.html`, o usuário apontou que o tema claro continua branco demais e excessivamente luminoso.
- A inspeção do CSS da prévia identificou fundo `#F6F6F6`, barras e cartões comuns em `#FFFFFF` e cartões elevados em `#EDEDED`; o registro deixa explícito que isso descreve a prévia, não uma auditoria completa do tema de produção.
- A questão foi associada à ideia existente IDEA-007, sem criar um novo ID nem duplicar o catálogo.
- Criado o documento [Direção em análise — refinamento do tema claro](PROPOSTAS/2026-11-21_ROTACAO_E_IDENTIDADE_VISUAL/DIRECAO_TEMA_CLARO.md), com a observação, critérios e três famílias conceituais para prototipagem: mineral neutro, off-white quente e grafite suave.
- Atualizados o catálogo central e o quadro de pendências para apontar ao registro detalhado.
- Estado: capturado / em análise. Nenhuma paleta foi aprovada; nenhuma alteração no tema de produção, código Android ou documentação técnica do aplicativo foi realizada por este registro.

## 2026-11-21 — Preparação do prompt de rotação e identidade visual

- Consolidado o pedido para investigar o BUG-001, sem implementar a correção antes de apresentar diagnóstico, evidências e plano.
- Registrada a direção de identidade tipográfica do NEXUS: wordmark original, monocromático, geométrico, com possibilidade de segmentos, cortes e espaços negativos.
- A imagem HUMANICA foi registrada somente como referência de linguagem visual, não como modelo a copiar.
- Preservado o ícone atual N branco sobre fundo preto; a remoção do N verde redundante deve ser tratada dentro de uma solução de identidade, sem substituição automática do ícone.
- Definida exploração de três direções visuais e aplicações seletivas em saudação/títulos, mantendo tipografia funcional no restante da interface.
- O prompt também incorpora os pontos positivos relatados na exploração de Gabriel: fluxo de seleção de data na Agenda, densidade moderada de registros e distinção entre alertas vermelhos (atraso) e amarelos (conflito).
- Criado pacote em `CHATGPT/PROPOSTAS/2026-11-21_ROTACAO_E_IDENTIDADE_VISUAL/`, com prompt integral e documento de direção tipográfica.
- Estado correto: prompt preparado e aprovado para encaminhamento; envio ao Google AI Studio ainda não confirmado. Nenhuma alteração de código foi realizada por este registro.

## 2026-11-21 — Organização pós-exploração do perfil Gabriel

- Registrado o resultado positivo relatado pelo usuário após restaurar e explorar Gabriel no aplicativo.
- Preservados como comportamentos positivos o fluxo de selecionar uma data na Agenda e consultar os itens abaixo, a densidade moderada de registros e a distinção de alertas vermelhos (atraso) e amarelos (conflito).
- Criada a pasta 05_OBSERVACOES_POS_TESTE dentro do experimento para separar problemas/correções, ideias de evolução, visão futura e observações positivas.
- Registrado BUG-001: rotação do dispositivo retorna à Home e perde conteúdo/progresso de edição, conforme relato do usuário. Causa técnica ainda desconhecida, não implementado.
- Registrado COR-001: remover o N verde redundante antes do nome NEXUS, mantendo o ícone atual N branco sobre fundo preto.
- Capturadas IDEA-001 a IDEA-011, cobrindo Agenda configurável, feriados BR/EUA, onboarding, Financeiro, Mercado, layouts adaptativos, temas, splash, animações, imagens em eventos e integração financeira.
- Separadas FUT-001 a FUT-003 como possibilidades futuras de IA e WhatsApp, sem prioridade ou aprovação.
- Atualizado o catálogo central apenas com um índice das ideias; detalhes e evidências continuam na pasta do experimento.
- A presença dos três JSONs foi conferida no repositório ativo em test-backups/. Os arquivos não foram duplicados no repositório documental.
- Nenhuma alteração de código do aplicativo foi feita nesta operação.

## 2026-11-21 — Organização do experimento de três vidas simuladas

- Criada a pasta CHATGPT/TESTES/2026-11-21_TESTE_VIDAS_SIMULADAS/ para manter juntos o contexto do experimento, os três perfis, o registro de acompanhamento e os resultados.
- Separados o relatório de geração/validação estática e o relatório de testes reais no aplicativo.
- Criado relatório específico explicando os motivos e o escopo das mudanças documentais.
- Atualizado o índice desta área para incluir experimentos e testes.
- Registrado em propostas ao AI Studio que a geração dos três backups foi reportada como executada.
- Decisão de organização: não criar um segundo catálogo de ideias. O catálogo central continua sendo 03_IDEIAS_E_BRAINSTORMS.md; observações do experimento poderão apontar para ele quando houver uma oportunidade de produto realmente nova.
- O prompt original integral não foi recuperado; o arquivo de prompt registra uma reconstrução fiel e identifica essa limitação.
- Nenhuma documentação técnica do aplicativo ou código foi alterada naquela operação.

## 2026-11-20 — Primeira consolidação estratégica

- Criada a estrutura inicial de CHATGPT.
- Registrada a visão do NEXUS como centro de comando pessoal e profissional.
- Separados princípios estratégicos, ideias, decisões, propostas encaminhadas e pendências.
- Incorporado o relatório de reorganização técnica como proposta enviada/executada no workspace, com limitações de Git, build, testes e ZIP preservadas.
- Registradas as regras de governança para não confundir ideias com decisões e decisões com implementação.
- Fontes consultadas: contexto persistente disponível, recuperação de conversas e documentos legados do repositório.

## Lacunas conhecidas

- A cronologia de todas as conversas ainda não foi reconstruída.
- Nem todos os prompts antigos foram recuperados em texto integral.
- Alguns estados de ideias antigas precisam de confirmação.
- A pasta GOOGLE_IA ainda não contém a nova documentação numerada 01–12; o relatório de AI Studio informa que ela foi criada no workspace, mas o repositório ativo GitHub ainda precisa receber esses arquivos.

## 2026-11-15 — Contexto inicial de produto e integração recuperado

- O NEXUS foi descrito como projeto pessoal, funcional e instalável, com prioridade no uso diário, não em comercialização inicial.
- Foi considerada a possibilidade de código open source e modificações por terceiros; não foi recuperada uma decisão formal de licença.
- Cada instalação deve iniciar vazia e manter dados locais isolados.
- Identidade visual inicial: base preta, letras brancas e símbolo “N”.
- Conceito “Meu Dia” aprovado conceitualmente, com navegação inicial Início, Agenda, Tarefas, Finanças e Mais; saudação branca e frase/autoria pequenas, cinzas e opcionais.
- Foram discutidos resumos/widgets Meu Dia, Minhas Tarefas, Financeiro e Resumo integrado; não inferir que todos estavam implementados.
- Houve build Debug com 18 erros Kotlin reportados em Dashboard/Quotes, Home, Backup/Diagnostics e Goals. Esse é um registro histórico, não estado atual.
- Foi discutida integração seletiva de arquivos gerados pelo Google AI Studio com o GitHub, preservando .github/workflows/, Gradle e configurações existentes. A comparação de ZIP foi proposta, mas o ZIP corrigido ainda não havia sido recebido naquele registro.
