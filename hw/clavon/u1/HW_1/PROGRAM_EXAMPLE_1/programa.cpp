#include <iostream>
#include <cmath>

using namespace std;

void imprimir_bienvenida();
void solicitar_binario(long &binario);
long convertir_binario_a_decimal(long binario);
void mostrar_resultado(long numero);

int main() {
    long binario, numero;

    imprimir_bienvenida();
    solicitar_binario(binario);
    numero = convertir_binario_a_decimal(binario);
    mostrar_resultado(numero);

    return 0;
}

void imprimir_bienvenida() {
    cout << "Bienvenido usuario, vamos a convertir un numero de binario a decimal." << endl;
    cout << endl;
}

void solicitar_binario(long &binario) {
    cout << "Ingrese un numero binario: ";
    cin >> binario;
    cout << endl;
}

long convertir_binario_a_decimal(long binario) {
    long sum = 0;
    long i = 0;
    long D = binario;
    long c, R;

    do {
        c = D / 10;
        R = D % 10;
        sum = sum + R * pow(2, i);
        D = c;
        i++;
    } while (c > 0);

    return sum;
}

void mostrar_resultado(long numero) {
    cout << "El numero convertido es: " << numero << endl;
    cout << endl;
}
