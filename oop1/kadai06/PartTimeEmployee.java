package oop1.kadai06;

/**
 * アルバイト従業員の情報を管理し、アルバイト特有の給与計算ロジックを提供する具象クラスです。
 */
public class PartTimeEmployee extends Employee {
    /** 所得税率（総支給額に対する割合） */
    public static final double INCOME_TAX_RATE_PARTTIME = 0.05;
    /** 課税最低額。総支給額がこれ未満の場合は所得税0 */
    public static final double MIN_TAXABLE_GROSS_PAY_PARTTIME = 80000.0;

    /** 実働時間 */
    private double hoursWorked;

    /**
     * 親クラスのコンストラクタを呼び出し、アルバイト固有のフィールド（実働時間）を初期化するコンストラクタです。
     * hourlyRate（時給）は親クラスのbasePayとして渡されます。
     *
     * @param employeeId  従業員ID
     * @param name        氏名
     * @param hourlyRate  時給
     * @param hoursWorked 実働時間
     */
    public PartTimeEmployee(String employeeId, String name, double hourlyRate, double hoursWorked) {
        super(employeeId, name, hourlyRate); // hourlyRateをbasePayとして扱う
        this.hoursWorked = hoursWorked;
    }

    /**
     * 総支給額を計算します。
     * 計算式: 時給（親クラスの basePay）× 実働時間（hoursWorked）。
     *
     * @return 総支給額
     */
    @Override
    public double calculateGrossPay() {
        return basePay * hoursWorked;
    }

    /**
     * 控除額の合計を計算します。
     * 計算式: 所得税のみ。
     *
     * @return 控除額合計
     */
    @Override
    public double calculateTotalDeductions() {
        return calculateIncomeTax();
    }

    /**
     * 従業員の種別名を返します。
     *
     * @return "アルバイト"
     */
    @Override
    public String getEmployeeTypeName() {
        return "アルバイト";
    }

    /**
     * 実働時間フィールドの値を返します。
     *
     * @return 実働時間
     */
    public double getHoursWorked() {
        return hoursWorked;
    }

    /**
     * 親クラスのbasePay（時給として扱っている値）を返します。
     *
     * @return 時給
     */
    public double getHourlyRate() {
        return basePay;
    }

    /**
     * 計算された所得税を返します。
     * 計算ロジック: calculateGrossPay() の結果がMIN_TAXABLE_GROSS_PAY_PARTTIME以上であれば、
     * calculateGrossPay() * INCOME_TAX_RATE_PARTTIMEを返します。そうでなければ 0.0 を返します。
     *
     * @return 計算された所得税
     */
    public double calculateIncomeTax() {
        if (calculateGrossPay() >= MIN_TAXABLE_GROSS_PAY_PARTTIME) {
            return calculateGrossPay() * INCOME_TAX_RATE_PARTTIME;
        } else {
            return 0.0;
        }
    }
}