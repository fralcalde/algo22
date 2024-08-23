package aed;

class Funciones {
    int cuadrado(int x) {
        return x * x;
    }

    double distancia(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    boolean esPar(int n) {
        return n % 2 == 0;
    }

    boolean esBisiesto(int n) {
        boolean div4 = n % 4 == 0;
        boolean div100 = n % 100 == 0;
        boolean div400 = n % 400 == 0;
        return div4 && (!div100 || div400);
    }

    int factorialIterativo(int n) {
        int res = 1;

        for(int i = 1; i <= n; i++) {
            res = res * i;
        }

        return res;
    }

    int factorialRecursivo(int n) {
        if(n == 0) {
            return 1;
        }

        return n * factorialRecursivo(n - 1);
    }

    boolean esPrimo(int n) {
        int cantDivisores = 0;

        for(int i = 1; i <= n; i++) {
            if(n % i == 0) {
                cantDivisores += 1;
            }
        }

        return cantDivisores == 2;
    }

    int sumatoria(int[] numeros) {
        int res = 0;

        for(int num : numeros) {
            res += num;
        }

        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        int i = 0;
        while(numeros[i] != buscado) {
            i++;
        }

        return i;
    }

    boolean tienePrimo(int[] numeros) {
        boolean tienePrimo = false;

        for(int num : numeros) {
            tienePrimo = tienePrimo || esPrimo(num);
        }

        return tienePrimo;
    }

    boolean todosPares(int[] numeros) {
        boolean todosPares = true;

        for(int num : numeros) {
            todosPares = todosPares && esPar(num);
        }
        
        return todosPares;
    }

    boolean esPrefijo(String s1, String s2) {
        boolean esPrefijo = s1.length() <= s2.length();

        for(int i = 0; i < s1.length() && esPrefijo; i++) {
            esPrefijo = esPrefijo && (s1.charAt(i) == s2.charAt(i));
        }

        return esPrefijo;
    }

    boolean esSufijo(String s1, String s2) {
        boolean esSufijo = s1.length() <= s2.length();

        for(int i = 0; i < s1.length() && esSufijo; i++) {
            int s1_i = s1.length() - 1 - i;
            int s2_i = s2.length() - 1 - i;

            esSufijo = esSufijo && (s1.charAt(s1_i) == s2.charAt(s2_i));
        }

        return esSufijo;
    }
}
