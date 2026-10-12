#include <iostream>
#include <cstdlib>
#include <cmath>

#define PI 3.1416

using namespace std;

void mostrarBienvenida();
void leerInputs(float &x, int &k);
void calcularArcsinSeries(float x, int k, float &radian, float &grados);
void mostrarResultados(float x, int k, float radian, float grados);

int main() {
    float x, radian = 0, grados = 0;
    int k;

    mostrarBienvenida();
    leerInputs(x, k);
    
    if (x < -1.0 || x > 1.0) {
        cout << "\nError: El valor de x debe estar en el rango de [-1, 1]." << endl;
        cout << "Matematicamente el arcoseno no esta definido fuera de este dominio." << endl << endl;
    } else {
        calcularArcsinSeries(x, k, radian, grados);
        mostrarResultados(x, k, radian, grados);
    }

    system("pause");
    return 0;
}

void mostrarBienvenida() {
    cout << "Bienvenido usuario, este es un programa para el calculo de arcoseno aproximado y conversion de radianes a grados." << endl;
    cout << "Calculos a realizar: Calculo de arcoseno y conversion de radianes a grados." << endl << endl;
}

void leerInputs(float &x, int &k) {
    cout << "Ingrese x (debe estar entre -1 y 1): ";
    cin >> x;
    cout << "Ingrese la cantidad de terminos: ";
    cin >> k;
}

void calcularArcsinSeries(float x, int k, float &radian, float &grados) {
    double sum = 0, term;
    int n, i;

    for (n = 0; n < k; n++) {
        double fact2n = 1.0;
        double factn = 1.0;
        double potx = 1.0;
        double pot4 = 1.0;
        double signo = 1.0;

        if (n % 2 != 0) {
            signo = -1.0;
        }

        for (i = 1; i <= 2 * n + 1; i++) {
            potx = potx * x;
        }

        for (i = 1; i <= n; i++) {
            pot4 = pot4 * 4.0;
        }

        for (i = 1; i <= 2 * n; i++) {
            fact2n = fact2n * i;
        }

        for (i = 1; i <= n; i++) {
            factn = factn * i;
        }

        term = (signo * fact2n * potx) / (pot4 * factn * factn * (2 * n + 1));
        sum = sum + term;
    }
    
    radian = (float)sum;
    grados = (float)(sum * 180.0 / PI);
}

void mostrarResultados(float x, int k, float radian, float grados) {
    cout << endl;
    cout << "--- Resultados para X = " << x << " con " << k << " terminos ---" << endl;
    cout << "Resultado en radianes: " << radian << endl;
    cout << "Resultado en grados: " << grados << endl;
}
