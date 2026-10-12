#include <iostream>
#include <cmath>

using namespace std;

#define PI 3.14159265358979323846

void imprimir_bienvenida();
void leer_datos(double &x, int &max);
double calcular_factorial(int n);
double calcular_arcsen(double x, int max);
void mostrar_resultado(double radianes);

int main() {
    double x, radianes;
    int max;

    imprimir_bienvenida();
    leer_datos(x, max);
    radianes = calcular_arcsen(x, max);
    mostrar_resultado(radianes);

    return 0;
}

void imprimir_bienvenida() {
    cout << "Bienvenido, vamos a calcular el arcoseno de x mediante series de Taylor." << endl;
    cout << endl;
}

void leer_datos(double &x, int &max) {
    do {
        cout << "Ingresa el valor de x (entre -1 y 1): ";
        cin >> x;
        if (x < -1.0 || x > 1.0) {
            cout << "Valor invalido. El dominio de arcsen(x) es [-1, 1]." << endl;
        }
    } while (x < -1.0 || x > 1.0);

    cout << "Ingresa la cantidad de terminos a calcular: ";
    cin >> max;
    cout << endl;
}

double calcular_factorial(int n) {
    double fact = 1.0;
    for (int i = 1; i <= n; i++) {
        fact *= i;
    }
    return fact;
}

double calcular_arcsen(double x, int max) {
    double sum = 0.0;
    for (int n = 0; n <= max; n++) {
        double fact_2n = calcular_factorial(2 * n);
        double fact_n = calcular_factorial(n);
        
        double numerador = fact_2n * pow(x, 2 * n + 1);
        double denominador = pow(4, n) * pow(fact_n, 2) * (2 * n + 1);
        
        sum += (numerador / denominador);
    }
    return sum;
}

void mostrar_resultado(double radianes) {
    double grados = radianes * (180.0 / PI);
    cout << "Resultado en Radianes: " << radianes << " rad" << endl;
    cout << "Resultado en Grados:   " << grados << " grados" << endl;
    cout << endl;
}
