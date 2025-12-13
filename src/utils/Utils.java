package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Utils {
    //Advent Of Code 2025 - Day01
    public static int moverDerecha(int cantidad, int puntero) {
        //Movimiento circular: sumas y haces módulo 100
        return (puntero + cantidad) % 100;
    }
    //Advent Of Code 2025 - Day01
    public static int moverIzquierda(int cantidad, int puntero) {
        //Para restar en un círculo: ((puntero - cantidad) % 100 + 100) % 100
        return ((puntero - cantidad) % 100 + 100) % 100;
    }
    
    public static int contadorNumerosDia6(Object[] lista) {
    	int contador = 0;
    	boolean numeroEmpezado = false;
    	
    	for(int i = 0; i < lista.length - 1; i++) {
    		for(int j=0; j < lista[i].toString().length(); j++) {
    			
    			if(lista[i].toString().charAt(j) > 47 && lista[i].toString().charAt(j) < 58) {
    				numeroEmpezado = true;
    			} else {
    				if (numeroEmpezado) {
    					contador ++;
    					numeroEmpezado = false;
    				}
    			}
    		}
    	}
    	return contador;
    }
    
    public static void separadorNumerosDia6(Object[] lista, int[][] listaSeparada, char[] listaOperadores) {
    	int numero = 0;
    	int contadorOperadores = 0;
    	int contador = 0;
    	boolean numeroEmpezado = false;
    	
    	for(int i = 0; i < lista.length; i++) {
    		for(int j=0; j < lista[i].toString().length(); j++) {
    			
    			if(lista[i].toString().charAt(j) > 47 && lista[i].toString().charAt(j) < 58) {
    				numeroEmpezado = true;
    				numero *= 10;
    				numero += lista[i].toString().charAt(j) - 48;
    			}
    			else if(lista[i].toString().charAt(j) == '+' || lista[i].toString().charAt(j) == '*') {
    				listaOperadores[contadorOperadores] = lista[i].toString().charAt(j);
    				contadorOperadores ++;
    			}
    			else {
    				if (numeroEmpezado) {
    					listaSeparada[i][contador] = numero;
						contador ++;
						numeroEmpezado = false;
    				}
    				numero = 0;
    			}
    		}
    		if (numeroEmpezado) {
				listaSeparada[i][contador] = numero;
				contador ++;
				numeroEmpezado = false;
			}
    		numero = 0;
    		contador = 0;
    		contadorOperadores = 0;
    	}
    }
    
    public static void separadorNumerosDia6Parte2(Object[] lista, int[][] listaSeparada, char[] listaOperadores) {
    	int numero = 0;
    	int contadorOperadores = 0;
    	int contador = 0;
    	boolean numeroEmpezado = false;
    	
    	
    	for(int j=0; j < lista[0].toString().length(); j++) {
    		for(int i = 0; i < lista.length - 1; i++) {
    			if(lista[i].toString().charAt(j) > 47 && lista[i].toString().charAt(j) < 58) {
    				numeroEmpezado = true;
    				numero *= 10;
    				numero += lista[i].toString().charAt(j) - 48;
    			}
    			numero = 0;
				if (numeroEmpezado) {
					listaSeparada[i][contador] = numero;
					contador ++;
					numeroEmpezado = false;
				}
			if(lista[i].toString().charAt(j) == '+' || lista[i].toString().charAt(j) == '*') {
				listaOperadores[contadorOperadores] = lista[i].toString().charAt(j);
				contadorOperadores ++;
			}
			}
    		numero = 0;
    		contador = 0;
    		contadorOperadores = 0;
    	}
    }
    
    //Advent Of Code 2025 - Day02
    public static long sumInvalidInRange(long L, long R) {
        long sum = 0;
        long pow10dMinus1 = 1; //10^(d-1), inicia en d=1 -> 10^0 = 1

        for (int d = 1; ; d++) {
            long pow10d = pow10dMinus1 * 10; //10^d
            long F = pow10d + 1; //10^d + 1
            long minInvalid = pow10dMinus1 * F; //Mínimo inválido para este d: 10^(d-1) * (10^d + 1)

            //Si el mínimo ya supera R, no hay más inválidos en este rango
            if (minInvalid > R) {
                break;
            }

            //Rango válido para k (k tiene d dígitos)
            long kMin = pow10dMinus1;
            long kMax = pow10d - 1;

            //k debe cumplir L <= k*F <= R
            long kLow = (L + F - 1) / F; //ceil(L/F)
            long kHigh = R / F;          //floor(R/F)

            //Intersectar con [kMin, kMax]
            if (kLow < kMin) kLow = kMin;
            if (kHigh > kMax) kHigh = kMax;

            if (kLow <= kHigh) {
                long count = kHigh - kLow + 1;
                //Suma de la progresión aritmética: F * (kLow + kHigh) * count / 2
                long segmentSum = F * (kLow + kHigh) * count / 2;
                sum += segmentSum;
            }

            //Preparar para la siguiente iteración
            pow10dMinus1 = pow10d;
        }

        return sum;
    }
 // Encuentra el primer índice donde el valor es >= target
    public static int lowerBound(List<Long> list, long target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static TreeSet<Long> generateInvalidNumbers(long maxLimit) {
        TreeSet<Long> set = new TreeSet<>();
        // L desde 1 hasta 5
        for (int L = 1; L <= 5; L++) {
            // r desde 2 hasta floor(10/L)
            int maxR = 10 / L;
            for (int r = 2; r <= maxR; r++) {
                int n = L * r; // número total de dígitos
                if (n > 10) continue;
                long powL = pow10(L);
                long powN = pow10(n);
                long S = (powN - 1) / (powL - 1); // (10^{n} - 1) / (10^L - 1)
                long repunitL = (powL - 1) / 9; // repunit de L unos
                long pMin = pow10(L-1);
                long pMax = pow10(L) - 1;
                // Para L=1, no saltamos p uniformes porque todos lo son.
                // Para L>=2, saltamos p que son repdigits (todos dígitos iguales)
                for (long p = pMin; p <= pMax; p++) {
                    if (L >= 2) {
                        // Comprobar si p es repdigit (todos dígitos iguales)
                        if (p % repunitL == 0) {
                            long q = p / repunitL;
                            if (q >= 1 && q <= 9) {
                                // Este número será generado por L=1 con r' = n (ya que n = L*r)
                                // Así que lo saltamos para evitar duplicados, pero en realidad lo generará L=1.
                                // Para evitar generarlo dos veces, lo saltamos aquí.
                                continue;
                            }
                        }
                    }
                    long N = p * S;
                    if (N > maxLimit) break; // Como p aumenta, N aumenta, así que podemos romper si N excede maxLimit
                    if (N <= maxLimit) {
                        set.add(N);
                    }
                }
            }
        }
        // Además, para L=1, r puede ser mayor que 10? No, porque n<=10.
        // Ya lo cubrimos en el bucle con L=1.
        return set;
    }

    public static long pow10(int exp) {
        long result = 1;
        for (int i = 0; i < exp; i++) result *= 10;
        return result;

	}
    
    
    
    public static int getMaxJoltage(String bank) {
        int maxJoltage = 0;
        char[] digits = bank.toCharArray();
        
        // Probar todos los pares (i, j) donde i < j
        for (int i = 0; i < digits.length; i++) {
            int firstDigit = digits[i] - '0'; // Convertir char a int
            
            for (int j = i + 1; j < digits.length; j++) {
                int secondDigit = digits[j] - '0';
                int currentJoltage = firstDigit * 10 + secondDigit;
                
                if (currentJoltage > maxJoltage) {
                    maxJoltage = currentJoltage;
                }
            }
        }
        
        return maxJoltage;
    }
    
    public static long getMaxJoltage(String bank, int k) {
        int n=bank.length();
        char[] digits=bank.toCharArray();
        int lastPos=-1;  //Última posición seleccionada
        long result=0;   //Número resultante de 12 dígitos
        
        for (int i=0;i<k;i++) {
            int start=lastPos + 1;
            int end=n -(k-i);  //Última posición posible para este dígito
            
            //Buscar el dígito máximo en el rango [start, end]
            char maxDigit = '0';
            int maxPos = start;
            for (int j = start; j <= end; j++) {
                if (digits[j] > maxDigit) {
                    maxDigit = digits[j];
                    maxPos = j;
                }
            }
            
            //Agregar dígito al resultado (multiplicar por 10 y sumar)
            result = result * 10 + (maxDigit - '0');
            lastPos = maxPos;  //Actualizar última posición seleccionada
        }
        
        return result;

	}
    
    public static int countFreshIngredients(String filePath) throws IOException {
        //Listas para almacenar los rangos frescos y los IDs disponibles
        List<Range> freshRanges = new ArrayList<>();
        List<Long> ingredientIds = new ArrayList<>();
        
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        boolean readingRanges = true; //Para saber si estamos leyendo rangos
        
        //Leer el archivo línea por línea
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            
            //Si encontramos una línea vacía, cambiamos a leer IDs
            if (line.isEmpty()) {
                readingRanges = false;
                continue;
            }
            
            if (readingRanges) {
                //Procesar línea como rango fresco
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    long start = Long.parseLong(parts[0]);
                    long end = Long.parseLong(parts[1]);
                    freshRanges.add(new Range(start, end));
                }
            } else {
                //Procesar línea como ID de ingrediente
                long id = Long.parseLong(line);
                ingredientIds.add(id);
            }
        }
        reader.close();
        
        //Contar cuántos IDs están en los rangos frescos
        int freshCount = 0;
        for (long id : ingredientIds) {
            if (isFresh(id, freshRanges)) {
                freshCount++;
            }
        }
        
        return freshCount;
    }
    
    //Método para verificar si un ID está en algún rango fresco
    private static boolean isFresh(long id, List<Range> freshRanges) {
        for (Range range : freshRanges) {
            if (id >= range.start && id <= range.end) {
                return true; //El ID está dentro de este rango
            }
        }
        return false; //No está en ningún rango
    }
    
    //Clase auxiliar para representar un rango
    static class Range {
        long start;
        long end;
        
        Range(long start, long end) {
            this.start = start;
            this.end = end;
        }

    }
    public static long countAllFreshIds(String filePath) throws IOException {
        //Lista para almacenar los rangos frescos
        List<Range> freshRanges = new ArrayList<>();
        
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        boolean readingRanges = true;
        
        //Leer el archivo línea por línea
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            
            //Si encontramos una línea vacía, cambiamos a leer IDs (aunque no los necesitamos)
            if (line.isEmpty()) {
                readingRanges = false;
                continue;
            }
            
            if (readingRanges) {
                //Procesar línea como rango fresco
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    long start = Long.parseLong(parts[0]);
                    long end = Long.parseLong(parts[1]);
                    freshRanges.add(new Range(start, end));
                }
            }
            //No necesitamos procesar los IDs para esta parte
        }
        reader.close();
        
        //Ordenar los rangos por su inicio
        Collections.sort(freshRanges, new Comparator<Range>() {
            @Override
            public int compare(Range r1, Range r2) {
                if (r1.start < r2.start) return -1;
                if (r1.start > r2.start) return 1;
                //Si empiezan igual, ordenar por fin
                if (r1.end < r2.end) return -1;
                if (r1.end > r2.end) return 1;
                return 0;
            }
        });
        
        //Fusionar rangos superpuestos
        List<Range> mergedRanges = mergeRanges(freshRanges);
        
        //Calcular el total de IDs frescos sumando las longitudes de los rangos fusionados
        long totalCount = 0;
        for (Range range : mergedRanges) {
            totalCount += (range.end - range.start + 1);
        }
        
        return totalCount;
    }
    
    //Método para fusionar rangos superpuestos
    private static List<Range> mergeRanges(List<Range> ranges) {
        if (ranges.isEmpty()) return new ArrayList<>();
        
        List<Range> merged = new ArrayList<>();
        Range current = ranges.get(0);
        
        for (int i = 1; i < ranges.size(); i++) {
            Range next = ranges.get(i);
            
            //Si los rangos se superponen o son adyacentes
            if (next.start <= current.end + 1) {
                // Fusionar: actualizar el fin del rango actual
                current.end = Math.max(current.end, next.end);
            } else {
                //No hay superposición, añadir el rango actual a la lista
                merged.add(current);
                current = next;
            }
        }
        
        //Añadir el último rango
        merged.add(current);
        
        return merged;
    }
    
    //Versión alternativa usando Set (para rangos pequeños)
    public static long countAllFreshIdsWithSet(String filePath) throws IOException {
        Set<Long> allFreshIds = new HashSet<>();
        
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        boolean readingRanges = true;
        
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            
            if (line.isEmpty()) {
                readingRanges = false;
                continue;
            }
            
            if (readingRanges) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    long start = Long.parseLong(parts[0]);
                    long end = Long.parseLong(parts[1]);
                    
                    //Añadir todos los IDs en este rango al conjunto
                    for (long id = start; id <= end; id++) {
                        allFreshIds.add(id);
                    }
                }
            }
        }
        reader.close();
        
        return allFreshIds.size();
    }
}
