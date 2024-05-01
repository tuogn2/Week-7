package bai1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        String input = inputStringFromUser();

        List<PhanTuDaThuc> square = init(input);
        Function<List<PhanTuDaThuc>, List<PhanTuDaThuc>> daoHam = step01();
        
        Function<List<PhanTuDaThuc>, String> join = joinpipeline();
        
        String re = daoHam.andThen(join).apply(square);
        System.out.println("Sau khi đạo hàm:"+ re);
        
        
        Scanner scanner = new Scanner(System.in);
       System.out.print("Nhập giá trị x: ");
       double x = scanner.nextDouble();
       scanner.close();
           
       
        Function<List<PhanTuDaThuc>, Double >resualtX = resualtX(x);
        
        Double result2 = resualtX.apply(square);
        System.out.println("Kết quả sau khi thay x vào đạo hàm: "+ result2);

        
        


    }

	private static Function<List<PhanTuDaThuc>, Double >resualtX(double x) {
	
        return terms -> {
            Double derivativeTerms =(double) 0;
            for (PhanTuDaThuc term : terms) {
                Double newResult = term.getHeSo() * Math.pow(x,term.getSoMu());
                derivativeTerms += newResult;
            }
            return derivativeTerms;
        };
    	
	}


    
    public static Function<List<PhanTuDaThuc>, String> joinpipeline() {
		return terms -> {
			String sb = "";
			int i = 0;
			for (PhanTuDaThuc term : terms) {
				if (i == 0) {
					sb += term.toString();
				}
				if (i > 0 && term.getHeSo() > 0) {
					sb += "+" + term.toString();
				}
				if (i > 0 && term.getHeSo() < 0) {
					sb += term.toString();
				}
				i++;

			}
			return sb;
		};
		
	
		
	}
	public static String join(List<PhanTuDaThuc> result1) {
		String sb = "";
		int i = 0;
		for (PhanTuDaThuc term : result1) {
			if(i == 0) {
				sb += term.toString();
			}
			if (i > 0 && term.getHeSo() > 0) {
				sb += "+" + term.toString();
			}
			if (i > 0 && term.getHeSo() < 0) {
				sb +=  term.toString();
			}
			i++;
				
		}
		return sb;
		
	
		
	}

    public static String inputStringFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập chuỗi đơn thức dính liền nhau ví dụ 3x^2-2x+2: ");
        String input = scanner.nextLine();
        
        return input;
    }
   



    private static Function<List<PhanTuDaThuc>, List<PhanTuDaThuc>> step01() {
        // Define the derivative operation
        return terms -> {
            List<PhanTuDaThuc> derivativeTerms = new ArrayList<>();
            for (PhanTuDaThuc term : terms) {
                float newHeSo = term.getHeSo() * term.getSoMu();
                int newSoMu;
				if (term.getSoMu() == 0) {
					newSoMu = 0;
				} else {
					newSoMu = term.getSoMu() - 1;
				}
                
				
                PhanTuDaThuc derivativeTerm = new PhanTuDaThuc(newHeSo, newSoMu);
                derivativeTerms.add(derivativeTerm);
            }
            return derivativeTerms;
        };
    }

    private static List<PhanTuDaThuc> init(String s) {
            // Tách chuỗi thành các đơn thức và chuyển đổi thành mảng
            String[] termStrings = s.split("\\s*(?=[+-])"); // Sử dụng positive lookahead để giữ lại dấu
            List<PhanTuDaThuc> terms = new ArrayList<>();
          

            // Xử lý từng đơn thức
            for (String term : termStrings) {
            	 float heSo ;
            	if(term.startsWith(term, 'x')) {
            		heSo = 1;
            	}else {
            		heSo = getFirstNumber(term);
            	}
                 
                int soMu = getPartAfterX(term);
				if (soMu == 0 && term.contains("x")) {
					soMu = 1;
				}
                if(term.contains("-")){
                    terms.add(new PhanTuDaThuc(-heSo, soMu));
                }else{
                    terms.add(new PhanTuDaThuc(heSo, soMu));
                }
            }

            return terms;
    }

    public static int getFirstNumber(String str) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        } else {
            return 0; // or throw an exception, return a default value, etc.
        }
    }

    public static int getPartAfterX(String str) {
        Pattern pattern = Pattern.compile("x\\^(\\d+)");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1)); // Group 1 contains the part after x^
        } else {
            return 0;
        }
    }
}