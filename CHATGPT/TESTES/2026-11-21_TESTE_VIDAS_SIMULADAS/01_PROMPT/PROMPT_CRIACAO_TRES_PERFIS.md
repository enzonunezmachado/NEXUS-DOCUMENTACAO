# Prompt de criação dos três perfis

**Data:** 21/11/2026  
**Estado:** prompt preparado e encaminhado ao Google AI Studio, conforme contexto e relatório de execução recebido.  
**Integridade textual:** reconstrução fiel do escopo e das instruções recuperáveis; não é cópia literal do prompt original, cujo texto integral não está disponível no histórico recuperado.

## Objetivo da instrução

Investigar a implementação e a documentação atuais do NEXUS e, com base no contrato real de backup, criar três arquivos JSON independentes de backup oficial para testar o aplicativo com vidas fictícias, completas e diferentes.

## Perfis a representar

1. **Gabriel Ferreira:** jovem universitário de Engenharia da Computação na USP, mora sozinho em São Paulo, administra uma pequena assistência técnica domiciliar de celulares e tablets, participa de eventos de empreendedorismo, mantém projetos próprios, tem família e amigos, organiza finanças pessoais e da atividade profissional e pesquisa intercâmbio.
2. **Helena Martins:** mulher de 69 anos, aposentada, viúva, de São João del-Rei/MG. Tem vida tranquila, família, netos, amigas, jardinagem, caminhadas e voluntariado. Cuida de casas familiares alugadas por valores acessíveis e faz bolos caseiros sob encomenda, distinguindo vendas de preparos gratuitos para a família.
3. **Ricardo Almeida:** empresário de 42 anos, casado e sem filhos, de Curitiba/PR. Administra uma distribuidora de alimentos regionais, com centro de distribuição, duas lojas e clientes comerciais. Coordena fornecedores, estoque, vendas, logística, equipe, pagamentos, recebimentos e decisões estratégicas, mantendo também projetos e finanças pessoais separados dos empresariais.

## Autonomia de investigação

Antes de gerar os arquivos, investigar diretamente o código e a documentação atuais para identificar:

- O formato oficial de backup e sua versão.
- Entidades, campos, enums, relações e restrições.
- Regras de recorrência, exceções, agenda, conflitos, tarefas e lembretes.
- Regras financeiras, faturas, parcelamentos, orçamentos, metas e vínculos.
- Configurações, personalização, Dashboard, briefings e recursos de mercado incluídos no backup.
- Recursos existentes menos óbvios que possam ser exercitados de maneira realista.

Não limitar os dados a uma lista fechada de exemplos. Usar a autonomia para explorar a cobertura do aplicativo, sem inventar funcionalidades que não existem ou inserir campos incompatíveis com o contrato real.

## Qualidade dos dados

Criar três backups independentes, coerentes e suficientemente variados. Cada perfil deve refletir sua realidade humana, com histórico, situação atual e compromissos futuros plausíveis. Variar estados e situações: itens concluídos, pendentes, atrasados, vencidos, planejados, recorrências, exceções, eventos gratuitos e pagos, compromissos, notificações e relações entre registros, conforme suporte real do aplicativo.

Manter coerência temporal, financeira e referencial. Evitar dados aleatórios ou registros sem contexto. Diferenciar receitas e despesas pessoais e profissionais quando o modelo permitir. Não transformar Helena em uma empresária ou imobiliária; não atribuir filhos a Ricardo.

## Restrições técnicas

- Não alterar código Android, arquitetura, navegação, UI, banco Room, DAOs, entidades, migrations ou lógica do aplicativo.
- Não inventar ou modificar o formato oficial do backup para acomodar os dados.
- Criar somente arquivos de dados de teste, em pasta apropriada fora do diretório de código/empacotamento do aplicativo.
- Não misturar os três perfis nem sobrescrever dados pessoais.
- Não realizar commit ou publicação sem autorização específica.
- Usar apenas funcionalidades e valores válidos no contrato investigado.

## Validação solicitada

Validar, no mínimo, sintaxe JSON, codificação, versão e estrutura do backup, campos e enums, IDs únicos, referências, restrições de unicidade, coerência de datas e valores, consistência de faturas e transações e compatibilidade de configurações opcionais.

Quando não houver ambiente Android disponível, declarar explicitamente que a validação foi estática e que a restauração real permanece pendente. Não afirmar teste de APK, emulador ou dispositivo que não tenha sido executado.

## Relatório final solicitado

Informar os arquivos e caminhos criados, versões do contrato, contagens por tipo de registro, funcionalidades cobertas, critérios e resultados de validação, limitações do ambiente e instruções para testar cada backup no aplicativo. Distinguir claramente o que foi inspecionado, gerado, validado estaticamente e efetivamente restaurado.
