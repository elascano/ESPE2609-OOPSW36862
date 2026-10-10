// programa.cpp

#include "Operaciones.h"
#include "Persona.h" 
#include <iostream>
#include <cstdlib>
#include <string>
#include <string.h>

using namespace std;

int main(int argc, char** argv) {
	// Declarar dos estructuras de tipo Persona.
	Persona P1, P2;
	// Imprimir los datos por defecto de inicialización de las estructuras.
	InicializarDatos(P1);
	ImprimirDatos(P1);
	cout << endl;
	InicializarDatos(P2);
	ImprimirDatos(P2);
	cout << endl;
	// Leer e imprimir los datos de la primera persona.
	cout << endl;
	cout << "Los datos de la primera persona son:" << endl;	
	LeerDatos(P1);
	ImprimirDatos(P1);
	// Leer e imprimir los datos de la segunda persona.
	cout << endl;
	cout << "Los datos de la segunda persona son:" << endl;	
	LeerDatos(P2);
	ImprimirDatos(P2);
	
	system("pause");
	return 0;
}
