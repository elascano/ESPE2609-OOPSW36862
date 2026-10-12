//Librerias

#include <stdio.h>
#include <math.h>

void inicializar(int vector[]);

int main(){
    int vector[10];

    inicializar(vector);

    //ingresar
    for (int i=0; i<10; i++){
        printf("Ingrese el valor %d: ", i+1);
        scanf("%d", &vector[i]);
    }

    //mostrar
    printf("\n\nVector: \n");
    for (int i=0; i<10; i++){
        printf("%d\t",vector[i]);
    }

    //ordenar burbuja
    for (int i=0; i<10; i++){
        for (int j=1; j<10; j++){
            if (vector[i]<vector[j]){
                int aux=vector[i];
                vector[i]=vector[j];
                vector[j]=aux;
            }
        }
    }

    //Arrays(vector);

    //mostrar
    printf("\n\nVector ordenado: \n");
    for (int i=0; i<10; i++){
        printf("%d\t",vector[i]);
    }
    return 0;
}
void inicializar(int vector[]){
    for (int i=0; i<10; i++){
        vector[i]=0;
    }
}
