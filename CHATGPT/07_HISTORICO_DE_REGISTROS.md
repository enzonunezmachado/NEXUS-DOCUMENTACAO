# Histórico de registros da memória estratégica

Este arquivo registra consolidações realizadas nesta pasta. Não é um log integral de conversas.

## 2026-11-21 — Organização pós-exploração do perfil Gabriel

- Registrado o resultado positivo relatado pelo usuário após restaurar e explorar Gabriel no aplicativo.
- Preservados como comportamentos positivos o fluxo de selecionar uma data na Agenda e consultar os itens abaixo, a densidade moderada de registros e a distinção de alertas vermelhos (atraso) e amarelos (conflito).
- Criada a pasta 05_OBSERVACOES_POS_TESTE dentro do experimento para separar problemas/correções, ideias de evolução, visão futura e observações positivas.
- Registrado BUG-001: rotação do dispositivo retorna à Home e perde conteúdo/progresso de edição, conforme relato do usuário. Causa técnica ainda desconhecida; correção não implementada.
- Registrado COR-001: solicitação de remover o N verde redundante antes do nome NEXUS, mantendo o ícone atual N branco sobre fundo preto.
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
- Incorporado o relatório de reorganização documental técnica como proposta enviada/executada no workspace, com limitações de Git, build, testes e ZIP preservadas.
- Registradas as regras de governança para não confundir ideias com decisões e decisões com implementação.
- Fontes consultadas: contexto persistente disponível, recuperação de conversas e documentos legados no repositório.

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
- Foram discutidos resumos/widgets Meu Dia, Minhas Tarefas, Financeiro e Resumo integrado; não inferir que todos já estavam implementados.
- Houve build Debug com 18 erros Kotlin reportados em Dashboard/Quotes, Home, Backup/Diagnostics e Goals. Esse é um registro histórico, não estado atual.
- Foi discutida integração seletiva de arquivos gerados pelo Google AI Studio com o GitHub, preservando .github/workflows/, Gradle e configurações existentes. A comparação de ZIP foi proposta, mas o ZIP corrigido ainda não havia sido recebido naquele registro.
