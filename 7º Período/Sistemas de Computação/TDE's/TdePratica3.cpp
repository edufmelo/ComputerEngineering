#include <iostream>
#include <unistd.h> 
#include <signal.h> 
#include <stdlib.h>
#include <time.h> 

using namespace std;

int ContadorSeg;

void nSleep(float t){ // Realizado em Sala de Aula
    struct timespec ts; 
    ts.tv_sec = t;
    ts.tv_nsec = (t - ts.tv_sec) * 1e9;
    nanosleep(&ts, nullptr);
}

void FilhoOK(int ns){
	cout << "Filho OK\n" ;
}

void FilhoTerminou(int ns){
	cout << "	Filho terminou\n" ;
}

void MensagemAlarme(int ns){
	ContadorSeg++;
	cout << "	[3 segundos]\n"; // Mostra alarme
    alarm(3); 
}

int main(int argc, char* argv[]){
	if (argc < 2) {
		cout << "Uso correto: " << argv[0] << " <intervalo_de_tempo>\n";
		return 1;
    }
    
    cout << "Pai iniciando\n";    
    float s = atof(argv[1]); // Converte para float
    cout << "Criando filho\n";
	
	pid_t p = fork();
	
	if(p!= 0){ // Filho = 0, logo p!=0 = Pai
		signal(SIGUSR1, FilhoOK); // Pai aguarda sinal, depois executa
		cout << "Pai aguardando...\n";		
		pause(); // Pause ate receber sinal - Desativa quando recebe
		
		nSleep(5);	
		cout << "Enviando confirmacao\n";
		
		kill(p, SIGUSR1); // Manda sinal para filho
		cout << "Pai terminou\n";	
	} else { // Filho = 0
		cout << "	Filho iniciando\n";
		signal(SIGALRM, MensagemAlarme); // Sinal de alarme
		alarm(3); // Executa sinal
		
		for (int i = 1; i <= 20; i++) { // Filho conta de 1 ate 20
			cout << "	[" << i << "]\n";
			nSleep(s);
		}
		
		cout << "	Imprimiu mensagem "<< ContadorSeg <<" vezes.\n";
		alarm(0); // Desativa alarme
		
		kill(getppid(), SIGUSR1); // Manda sinal para pai
		cout << "	Aguardando confirmacao do pai...\n";
		
		signal(SIGUSR1, FilhoTerminou); // Filho aguarda sinal, depois executa
		pause(); // Pause ate receber sinal - Desativa quando recebe
	}

	return 0;
}

