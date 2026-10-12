// Operaciones.cpp

#include "Operaciones.h"
#include <iostream>
#include <cstdlib>
#include <string.h>

using namespace std;

void InicializarDatos(Persona &P)
{
	fflush(stdin); // Vaciar el buffer del teclado.
	strcpy(P.nombre, "DEFAULT");
	strcpy(P.direccion, "DEFAULT");
	P.edad = 0;
	P.altura = 0.0f;
	P.peso = 0.0f;
}
void LeerDatos(Persona &P)
{
	fflush(stdin); // Vaciar el buffer del teclado.
	cout << "Ingrese el nombre: "; gets(P.nombre);
	cout << "Ingrese la dirección: "; gets(P.direccion);
	cout << "Ingrese la edad: "; cin >> P.edad;
	cout << "Ingrese el peso: "; cin >> P.peso;
	cout << "Ingrese la altura: "; cin >> P.altura;
}
void ImprimirDatos(Persona P)
{
	cout << endl;
	cout << "Nombre: " << P.nombre << endl;
	cout << "Dirección: " << P.direccion << endl;
	cout << "Edad: " << P.edad << endl;
	cout << "Peso: " << P.peso << endl;
	cout << "Altura: " << P.altura << endl;
}






