//declarei uma variável numérica para as horas de trabalho (8 horas)
let horasTrabalho = 8; //'let' cria uma variável mutável que armazena o número 8.

//declarei uma variável numérica para as horas de estudos diários (3 horas)
let horasEstudos = 3; //armazena o número 3

//declarei uma variável numérica para as horas de sono (7 horas)
let horasSono = 7; //armazena o número 7

//declarei uma variável numérica para o total de horas em um dia (fixo em 24 horas)
let totalHorasDia = 24; //armazena o número 24, representando um dia completo

//somei as horas ocupadas (trabalho + estudos + sono) para obter total de horas usadas
let horasOcupadas = horasTrabalho + horasEstudos + horasSono;

//subtraí as horas ocupadas do total do dia para calcular o tempo livre restante
let tempoLivre = totalHorasDia - horasOcupadas; //calcula 24 - 18 = 6. resultado em "tempo livre"

//insere informações detalhadas no HTML, nos parágrafos específicos

// Seleciona o elemento HTML com id "horasTrabalho" e insere o texto com a quantidade de horas de trabalho diárias
document.getElementById("horasTrabalho").textContent = `Horas de trabalho diárias: ${horasTrabalho} horas`;

// Seleciona o elemento HTML com id "horasEstudos" e insere o texto com a quantidade de horas de estudos diários
document.getElementById("horasEstudos").textContent = `Horas de estudos diárias: ${horasEstudos} horas`;

//Seleciona o elemento HTML com id "horasSono" e insere o texto com a quantidade de horas de sono diárias
document.getElementById("horasSono").textContent = `Horas de sono diárias: ${horasSono} horas`;

// Seleciona o elemento HTML com id "tempoLivre" e insere o texto com o cálculo do tempo livre disponível no dia
document.getElementById("tempoLivre").textContent = `Tempo livre no dia: ${tempoLivre} horas`;

