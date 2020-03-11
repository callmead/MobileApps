package edu.utep.cs.cs4330.tuitioncalculation;

public class TuitionCalculator {
    public int totalFee = 0;
    public int tuition_fee = 0;
    public int fee = 0;

    private double UG_major_fee = 150.00; //per semester
    private double G_major_fee = 165.00; //per semester
    private double lib_fee = 11.50; //per credit hour
    private double tech_fee = 25.0; //per credit hour
    private double max_fee = 375.0; //15 credit hour

    public void setCreditHours(String studentType, int hours){
        if(studentType.equals("Undergraduate")) {
            tuition_fee = (int) (UG_major_fee * hours);
            fee = (int) ((lib_fee * hours) + (tech_fee * hours));
            totalFee = tuition_fee + fee;
        }else if(studentType.equals("Graduate")){
            tuition_fee = (int) (G_major_fee * hours);
            fee = (int) ((lib_fee * hours) + (tech_fee * hours));
            totalFee = tuition_fee + fee;
        }else{}
    }
}
