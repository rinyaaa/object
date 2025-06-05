// TodoListApp.java
package week07.oop1.todoapp;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * TODOリストアプリケーションのメインクラスです。
 * GUIの構築とイベント処理を担当します。
 * Taskableインターフェースを実装したTaskクラスを利用します。
 */
public class TodoListApp {
    private JFrame frame;                     // メインウィンドウ
    private DefaultListModel<Task> listModel; // JListのモデル (Taskオブジェクトを格納)
    private JList<Task> taskList;             // タスク表示用リスト (Taskオブジェクトを表示)
    private JTextField taskInput;             // タスク内容入力用テキストフィールド
    private JTextField dueDateInput;            // 期限日入力用テキストフィールド
    private List<Task> tasks;                 // タスクを格納するArrayList (Taskオブジェクトのリスト)

    /**
     * アプリケーションを初期化し、GUIを表示します。
     */
    public TodoListApp() {
        // データ構造の初期化
        tasks = new ArrayList<>();
        listModel = new DefaultListModel<>();

        // メインフレームの設定
        frame = new JFrame("TODOリスト");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 450);
        frame.setLayout(new BorderLayout(5, 5)); // コンポーネント間の隙間を設定

        // 入力パネルの作成
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5)); // 左揃え、コンポーネント間隔5px
        // タスク内容入力フィールド
        inputPanel.add(new JLabel("タスク内容:"));
        taskInput = new JTextField(20); // 幅の目安として20文字分
        inputPanel.add(taskInput);
        // 期限日入力フィールド
        inputPanel.add(new JLabel("期限日 (YYYY-MM-DD):"));
        dueDateInput = new JTextField(10); // 幅の目安として10文字分 (YYYY-MM-DD)
        inputPanel.add(dueDateInput);

        // 追加ボタン
        JButton addButton = new JButton("追加");
        addButton.addActionListener(e -> addTask()); // 追加ボタンの処理実装
        inputPanel.add(addButton);
        frame.add(inputPanel, BorderLayout.NORTH); // フレームの上部に入力パネルを追加

        // タスク表示リストの作成
        taskList = new JList<>(listModel); // listModelを使用してJListを初期化
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 単一選択モードに設定
        taskList.setCellRenderer(new TaskCellRenderer()); // taskListのセルレンダラーを定義

        JScrollPane scrollPane = new JScrollPane(taskList); // リストをスクロール可能にする
        frame.add(scrollPane, BorderLayout.CENTER); // フレームの中央にリストを追加

        // 操作ボタンパネルの作成
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)); // 中央揃え、コンポーネント間隔(左右10px, 上下5px)
        // 完了/未完了切り替えボタン
        JButton toggleCompleteButton = new JButton("完了/未完了");
        toggleCompleteButton.addActionListener(e -> toggleTaskCompletion()); // 完了/未完了切り替えボタンの処理実装
        buttonPanel.add(toggleCompleteButton);

        // 削除ボタン
        JButton deleteButton = new JButton("削除");
        deleteButton.addActionListener(e -> deleteTask()); // 削除ボタンの処理実装
        buttonPanel.add(deleteButton);

        // 期限日ソートボタン
        JButton sortByDueDateButton = new JButton("期限日でソート");
        sortByDueDateButton.addActionListener(e -> sortTasksByDueDate()); // 期限日ソートボタンの処理実装
        buttonPanel.add(sortByDueDateButton);

        frame.add(buttonPanel, BorderLayout.SOUTH); // フレームの下部にボタンパネルを追加

        // フレームを画面中央に表示し、可視化
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * タスクを追加します。
     * テキストフィールドに入力された内容と期限日を使い新しいTaskオブジェクトを作成し、リストに追加します。
     * 初期状態は未完了とします。追加後、入力フィールドは空にします。
     */
    private void addTask() {
        String description = taskInput.getText().trim();
        if (description.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "タスク内容を入力してください。", "入力エラー", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String dueDateString = dueDateInput.getText().trim();
        LocalDate dueDate = Taskable.parseDueDate(dueDateString);

        if (!dueDateString.isEmpty() && dueDate == null) {
            JOptionPane.showMessageDialog(frame, "不正な期限日形式です。YYYY-MM-DD形式で入力してください。", "入力エラー", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Task newTask = new Task(description, dueDate);
        tasks.add(newTask);
        updateTaskListDisplay();

        taskInput.setText(""); // 入力フィールドをクリア
        dueDateInput.setText(""); // 入力フィールドをクリア
    }

    /**
     * 選択されたタスクの完了状態を切り替えます。
     * JListの表示も更新され、完了したタスクは視覚的に区別できるようにします。
     */
    private void toggleTaskCompletion() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            Task selectedTask = listModel.getElementAt(selectedIndex);
            selectedTask.setCompleted(!selectedTask.isCompleted());
            updateTaskListDisplay(); // 表示を更新
        } else {
            JOptionPane.showMessageDialog(frame, "タスクを選択してください。", "選択エラー", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * 選択されたタスクをリストから削除します。
     */
    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            Task selectedTask = listModel.getElementAt(selectedIndex);
            tasks.remove(selectedTask); // ArrayListから削除
            updateTaskListDisplay(); // JListの表示を更新
        } else {
            JOptionPane.showMessageDialog(frame, "タスクを選択してください。", "選択エラー", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * タスクリストを期限日の昇順でソートし、表示を更新します。
     * 期限日が未設定のタスクは最後に配置します。
     */
    private void sortTasksByDueDate() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                LocalDate d1 = t1.getDueDate();
                LocalDate d2 = t2.getDueDate();

                // 両方nullの場合、順序は変わらない
                if (d1 == null && d2 == null) {
                    return 0;
                }
                // t1がnullでt2がnullでない場合、t1を後にする
                if (d1 == null) {
                    return 1;
                }
                // t2がnullでt1がnullでない場合、t2を後にする
                if (d2 == null) {
                    return -1;
                }
                // 両方nullでない場合、日付で比較
                return d1.compareTo(d2);
            }
        });
        updateTaskListDisplay();
    }

    /**
     * tasksリストの内容をJListに反映し、表示を更新します。
     */
    private void updateTaskListDisplay() {
        listModel.clear(); // JListのモデルをクリア
        for (Task task : tasks) {
            listModel.addElement(task); // ArrayListの全タスクをJListに追加
        }
        // JListの表示を再描画
        taskList.repaint();
    }

    /**
     * アプリケーションのエントリーポイントです。
     * SwingUtilities.invokeLaterを使用して、イベントディスパッチスレッドでGUIを起動します。
     *
     * @param args コマンドライン引数 (このアプリケーションでは未使用)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TodoListApp());
    }
}