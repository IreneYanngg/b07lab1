// sep 26th

import java.io.IOException;

public class Driver {
	public static void main(String [] args) {
		
		double[] co1 = {2, -3.0, 1.0, 2};   // 2.0x^3 - 3.0 + x^2 + 2.0x
        int[] exp1 = {3, 0, 2, 1};
        Polynomial p1 = new Polynomial(co1, exp1);
        System.out.println("p1, x=1 [outputs 2]: " + p1.evaluate(1));  // outputs 2
        System.out.println("p1, x=0 [outputs -3]: " + p1.evaluate(0));  // outputs -3

        // Test Case 2: 常数多项式 & evaluate 测试
        double[] co2 = {3};   // 常数多项式：3
        int[] exp2 = {0};
        Polynomial p2 = new Polynomial(co2, exp2);
        System.out.println("p2 (constant polynomial, x=1) [outputs 3]: " + p2.evaluate(1));  // outputs 3
        System.out.println("p2 (constant polynomial, x=10) [outputs 3]: " + p2.evaluate(10));  // outputs 3
        System.out.println("p2 (constant polynomial, x=0) [outputs 3]: " + p2.evaluate(10));  // outputs 3
        

        // Test Case 3: 只有一个项
        double[] co3 = {3};
        int[] exp3 = {2};   // 3x^2
        Polynomial p3 = new Polynomial(co3, exp3);
        System.out.println("p3 (3x^2, x=0) [outputs 0]: " + p3.evaluate(0));  // outputs 0
        System.out.println("p3 (3x^2, x=1) [outputs 3]: " + p3.evaluate(1));  // outputs 3
        System.out.println("p3 (3x^2, x=-2) [outputs 12]: " + p3.evaluate(-2));  // outputs 12
        
        // Test Case 3-2
        double[] co3_2 = {3, 1, 1};
        int[] exp3_2 = {3, 0, 2}; // 3x3+1+x2
        Polynomial p3_2 = new Polynomial(co3_2, exp3_2);
        System.out.println("p3_2 (3x3+1+x2, x=-2) [outputs -19]: " + p3_2.evaluate(-2));
        try {
        	p3_2.saveToFile("/Users/ireneyang/Desktop/CSCB07/Labs/b07lab1/polynomial_3_2.txt");
            System.out.println("Polynomial p3_2 saved to polynomial_3_2.txt");
        } catch (IOException e) {
            System.out.println("Failed to save polynomial p3_2 to file.");
        }

        // Test Case 4: 多项式相加
        Polynomial sum = p1.add(p3);  // p1 + p3
        System.out.println("Sum of p1 and p3 (x=1) [outputs 5]: " + sum.evaluate(1));  // outputs 5
        System.out.println("Sum of p1 and p3 (x=0) [outputs -3]: " + sum.evaluate(0));  // outputs -3

        // Test Case 5: 多项式相乘
        Polynomial product = p1.multiply(p3);  // p1 * p3
        System.out.println("Product of p1 and p3 (x=1) [outputs 6]: " + product.evaluate(1));  // outputs 6
        try {
        	product.saveToFile("/Users/ireneyang/Desktop/CSCB07/Labs/b07lab1/polynomial_multiply.txt");
            System.out.println("Polynomial p1*p3 saved to polynomial_multiply.txt");
        } catch (IOException e) {
            System.out.println("Failed to save polynomial p1*p3 to file.");
        }
        
        double[] co_multi_1 = {2, 1, -4, 1};
        int[] exp_mult1_1 = {2, 0, 3, 1};
        Polynomial p_multi_1 = new Polynomial(co_multi_1, exp_mult1_1); // 2x2+1-4x3+x
        double[] co_multi_2 = {2, 1, 1};
        int[] exp_mult1_2 = {1, 4, 0};
        Polynomial p_multi_2 = new Polynomial(co_multi_2, exp_mult1_2); // 2x+x4+1
        Polynomial product_2 = p_multi_1.multiply(p_multi_2); // -4x7+2x6+x5-7x4+4x2+3x+1
        
        try {
        	product_2.saveToFile("/Users/ireneyang/Desktop/CSCB07/Labs/b07lab1/polynomial_multiply_0.txt");
            System.out.println("Polynomial p_multi_1*p_multi_2 saved to polynomial_multiply_0.txt");
        } catch (IOException e) {
            System.out.println("Failed to save polynomial p_multi_1*p_multi_2 to file.");
        }
        
        


        // Test Case 6: 测试删除 0 系数的项
        double[] co4 = {-4, 5};   // -4x^2 + 5
        int[] exp4 = {2, 0};
        Polynomial p4 = new Polynomial(co4, exp4);
        Polynomial sumWithZeros = p1.add(p4); 
        System.out.println("Sum of p1 and p4 (evaluated at x=1) [outputs 3]: " + sumWithZeros.evaluate(1));
        try {
        	sumWithZeros.saveToFile("/Users/ireneyang/Desktop/CSCB07/Labs/b07lab1/polynomial_add.txt");
            System.out.println("Polynomial p1+p4 saved to polynomial_add.txt");
        } catch (IOException e) {
            System.out.println("Failed to save polynomial p1+p4 to file.");
        }
        
        double[] co5 = {2, 3, 6};
        int[] exp5 = {1, 2, 0};
        Polynomial p5 = new Polynomial(co5, exp5); // 2x+3x2+6
        double[] co6 = {-2, -3, -2};
        int[] exp6 = {2, 0, 1};
        Polynomial p6 = new Polynomial(co6, exp6); // -2x2-3-2x
        Polynomial sumWithZeros_2 = p5.add(p6); 
        try {
        	sumWithZeros_2.saveToFile("/Users/ireneyang/Desktop/CSCB07/Labs/b07lab1/polynomial_co_add_to_0.txt");
            System.out.println("Polynomial p5+p6 saved to polynomial_co_add_to_0.txt");
        } catch (IOException e) {
            System.out.println("Failed to save polynomial p5+p6 to file.");
        }
        
        double[] co7 = {1};
        int[] exp7 = {1};
        Polynomial p7 = new Polynomial(co7, exp7); // x
        double[] co8 = {-1};
        int[] exp8 = {1};
        Polynomial p8 = new Polynomial(co8, exp8); // -x
        Polynomial sum_to_0 = p7.add(p8);
        try {
        	sum_to_0.saveToFile("/Users/ireneyang/Desktop/CSCB07/Labs/b07lab1/polynomial_co_add_to_0_2.txt");
            System.out.println("Polynomial p7+p8 saved to polynomial_co_add_to_0_2.txt");
        } catch (IOException e) {
            System.out.println("Failed to save polynomial p7+p8 to file.");
        }
        
        

        // Test Case 7: hasRoot 测试
        if (p1.hasRoot(1)) {
            System.out.println("1 is a root of p1 (wrong)");
        } else {
            System.out.println("1 is not a root of p1 (right)");
        }
        
        double[] root_test_co = {6};
        int[] root_test_exp = {0};
        Polynomial root_test_constant_6 = new Polynomial(root_test_co, root_test_exp);
        if (root_test_constant_6.hasRoot(0)) {
            System.out.println("0 is a root of root_test_constant_6 (wrong)");
        } else {
            System.out.println("0 is not a root of root_test_constant_6 (right)");
        }
        
        double[] root_test_co_2 = {0};
        int[] root_test_exp_2 = {0};
        Polynomial root_test_constant_0 = new Polynomial(root_test_co_2, root_test_exp_2);
        if (root_test_constant_0.hasRoot(8)) {
            System.out.println("8 is a root of root_test_constant_0 (right)");
        } else {
            System.out.println("8 is not a root of root_test_constant_0 (wrong)");
        }
        
        if (root_test_constant_0.hasRoot(0)) {
            System.out.println("0 is a root of root_test_constant_0 (right)");
        } else {
            System.out.println("0 is not a root of root_test_constant_0 (wrong)");
        }
        

        // Test Case 8: 保存多项式到文件
        try {
            p1.saveToFile("/Users/ireneyang/Desktop/CSCB07/Labs/b07lab1/polynomial_p1.txt");
            System.out.println("Polynomial p1 saved to polynomial_p1.txt");
        } catch (IOException e) {
            System.out.println("Failed to save polynomial p1 to file.");
        }

        // Test Case 9: 空多项式测试 【no-argument constructor相关】
        Polynomial emptyPoly = new Polynomial();  // 空的多项式
        System.out.println("Empty polynomial evaluated at x=1: " + emptyPoly.evaluate(1));  // 应该输出 0
        
        try {
        	emptyPoly.saveToFile("/Users/ireneyang/Desktop/CSCB07/Labs/b07lab1/polynomial_empty.txt");
            System.out.println("Polynomial emptyPoly saved to polynomial_empty.txt");
        } catch (IOException e) {
            System.out.println("Failed to save polynomial p1 to file.");
        }
		
	}
	
	
}

