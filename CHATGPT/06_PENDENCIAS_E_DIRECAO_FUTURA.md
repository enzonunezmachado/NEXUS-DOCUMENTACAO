# Pendências estratégicas e direção futura

**Tipo:** quadro de acompanhamento estratégico  
**Status:** inicial; validar contra estado técnico atual antes de abrir novos lotes

## 1. Pendências e ideias futuras registradas

| Tema | Estado estratégico | Observação / próximo critério |
|---|---|---|
| Recorrência de compromissos | Planejada | Definir regras e exceções; confirmar implementação antes de novo lote |
| Recorrência de eventos | Planejada | Evitar duplicidade e preservar série/ocorrências |
| Eventos com local e banner | Planejada | Definir experiência, armazenamento e backup |
| Backup ZIP com mídias | Planejada | Complementar, não quebrar JSON legado |
| Assistente de IA contextual | Em avaliação | Definir escopo, privacidade, custo e autonomia |
| Integrações externas opcionais | Futuro | Não comprometer operação offline nem exigir conta |
| Personalização da Home | Em análise | Fechar regras de reordenação e ativação de blocos |
| Refinamentos de Agenda e Financeiro | Em evolução | Consultar especificação e estado técnico atual |

## 2. Débitos e verificações técnicas reportadas em 20/11/2026

Estes itens vieram do relatório de reorganização técnica e não são decisões estratégicas novas:
- Build, testes unitários e APK não verificados no ambiente utilizado.
- Testes instrumentados ausentes para migrações Room reais, SAF, Doze, BootReceiver, notificações/deep-links e Glance.
- Risco residual na coordenação física Room × arquivos/SharedPreferences durante interrupção abrupta.
- Arquivos centrais extensos, incluindo NexusRepository.kt e EntityEditors.kt.
- Ausência de `gradlew` e de `app/schemas/` no estado inspecionado.
- ZIP real não exportado/inspecionado.
- Commit Git não realizado no container.

Consultar GOOGLE_IA para detalhes e confirmar se houve atualização posterior. Não converter automaticamente esses débitos em tarefas aprovadas.

## 3. Próximos marcos do trabalho documental

1. Publicar/sincronizar a documentação técnica atualizada em `GOOGLE_IA/` quando os arquivos versionados atualizados estiverem disponíveis no repositório ativo.
2. Completar a recuperação do histórico estratégico usando conversas e documentos legados.
3. Relacionar decisões e propostas com documentos técnicos e ADRs quando houver correspondência.
4. Manter um registro de mudanças por sincronização.
5. Revisar este quadro após cada nova decisão relevante.

## 4. Direção de longo prazo

O NEXUS deve continuar evoluindo. Módulos, tecnologias e interfaces podem mudar profundamente. A memória deve preservar por que decisões foram tomadas e como o produto evoluiu, sem obrigar versões futuras a reproduzir escolhas antigas.
