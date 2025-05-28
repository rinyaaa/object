package oop1.kadai06;

/**
 * 正社員の情報を管理し、正社員特有の給与計算ロジックを提供する具象クラスです。
 */
public class FullTimeEmployee extends Employee implements CommuteAllowanceCalculable {
    /** 月の平均所定労働時間 */
    public static final double STANDARD_MONTHLY_HOURS = 160.0;
    /** 残業手当の割増率 */
    public static final double OVERTIME_RATE_MULTIPLIER = 1.25;
    /** 社会保険料率（基本給に対する割合） */
    public static final double SOCIAL_INSURANCE_RATE = 0.15;
    /** 所得税率（総支給額に対する割合） */
    public static final double INCOME_TAX_RATE_FULLTIME = 0.10;

    /** 残業時間 */
    private double overtimeHours;
    /** 賞与額 */
    private double bonus;
    /** 交通費 */
    private double commuteAllowance;

    /**
     * 親クラスのコンストラクタを呼び出し、正社員固有のフィールド（残業時間、賞与、交通費）を初期化するコンストラクタです。
     *
     * @param employeeId 従業員ID
     * @param name 氏名
     * @param basePay 基本給
     * @param overtimeHours 残業時間
     * @param bonus 賞与額
     * @param commuteAllowance 交通費
     */
    public FullTimeEmployee(String employeeId, String name, double basePay, double overtimeHours, double bonus, double commuteAllowance) {
        super(employeeId, name, basePay);
        this.overtimeHours = overtimeHours;
        this.bonus = bonus;
        this.commuteAllowance = commuteAllowance;
    }

    /**
     * 総支給額（各種手当込み、控除前）を計算します。
     * 計算式: 基本給（basePay）+ 残業手当 + 賞与（bonus）+ 交通費（commuteAllowance）。
     *
     * @return 総支給額
     */
    @Override
    public double calculateGrossPay() {
        return basePay + calculateOvertimePay() + bonus + commuteAllowance;
    }

    /**
     * 控除額の合計を計算します。
     * 計算式: 社会保険料 + 所得税。
     *
     * @return 控除額合計
     */
    @Override
    public double calculateTotalDeductions() {
        return calculateSocialInsurance() + calculateIncomeTax();
    }

    /**
     * 従業員の種別名を返します。
     *
     * @return "正社員"
     */
    @Override
    public String getEmployeeTypeName() {
        return "正社員";
    }

    /**
     * 交通費フィールドの値を返します。
     *
     * @return 交通費
     */
    @Override
    public double getCommuteAllowance() {
        return commuteAllowance;
    }

    /**
     * 計算された残業手当を返します。
     * 計算式: (basePay / STANDARD_MONTHLY_HOURS) * OVERTIME_RATE_MULTIPLIER * overtimeHours
     * STANDARD_MONTHLY_HOURS が0以下の場合、0を返します。
     *
     * @return 計算された残業手当
     */
    public double calculateOvertimePay() {
        if (STANDARD_MONTHLY_HOURS <= 0) {
            return 0.0;
        }
        return (basePay / STANDARD_MONTHLY_HOURS) * OVERTIME_RATE_MULTIPLIER * overtimeHours;
    }

    /**
     * 賞与フィールドの値を返します。
     *
     * @return 賞与額
     */
    public double getBonus() {
        return bonus;
    }

    /**
     * 計算された社会保険料を返します。
     * 計算式: basePay * SOCIAL_INSURANCE_RATE
     *
     * @return 計算された社会保険料
     */
    public double calculateSocialInsurance() {
        return basePay * SOCIAL_INSURANCE_RATE;
    }

    /**
     * 計算された所得税を返します。
     * 計算式: calculateGrossPay() * INCOME_TAX_RATE_FULLTIME
     *
     * @return 計算された所得税
     */
    public double calculateIncomeTax() {
        return calculateGrossPay() * INCOME_TAX_RATE_FULLTIME;
    }

    /**
     * 残業時間フィールドの値を返します。
     *
     * @return 残業時間
     */
    public double getOvertimeHours() {
        return overtimeHours;
    }
}