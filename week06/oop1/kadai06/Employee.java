package oop1.kadai06;

public abstract class Employee {
    /** 従業員ID */
    protected String employeeId;
    /** 氏名 */
    protected String name;
    /** 基本給。月給制の場合は月額、時給制の場合は時給を想定 */
    protected double basePay;

    /**
     * 従業員ID、氏名、基本給を初期化するコンストラクタです。
     *
     * @param employeeId 従業員ID
     * @param name       氏名
     * @param basePay    基本給
     */
    public Employee(String employeeId, String name, double basePay) {
        this.employeeId = employeeId;
        this.name = name;
        this.basePay = basePay;
    }

    /**
     * 総支給額（各種手当込み、控除前）を計算して返します。
     * 具体的な計算はサブクラスで行います。
     *
     * @return 総支給額
     */
    public abstract double calculateGrossPay();

    /**
     * 控除額の合計を計算して返します。
     * 具体的な計算はサブクラスで行います。
     *
     * @return 控除額合計
     */
    public abstract double calculateTotalDeductions();

    /**
     * 従業員の種別名（例: "正社員", "アルバイト"）を返します。
     *
     * @return 従業員の種別名
     */
    public abstract String getEmployeeTypeName();

    /**
     * 従業員IDフィールドの値を返します。
     *
     * @return 従業員ID
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * 氏名フィールドの値を返します。
     *
     * @return 氏名
     */
    public String getName() {
        return name;
    }

    /**
     * 基本給フィールドの値を返します。
     *
     * @return 基本給
     */
    public double getBasePay() {
        return basePay;
    }

    /**
     * 差引支給額（手取り額）を計算します。
     *
     * @return 差引支給額
     */
    public double calculateNetPay() {
        return calculateGrossPay() - calculateTotalDeductions();
    }
}