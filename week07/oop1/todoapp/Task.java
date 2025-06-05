// Task.java
package week07.oop1.todoapp;

import java.time.LocalDate;

/**
 * Taskableインターフェースを実装するタスククラスです。
 * タスクの内容、期限日、完了状態を保持します。
 */
public class Task implements Taskable {
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    /**
     * 新しいタスクを作成します。
     *
     * @param description タスクの内容。
     * @param dueDate     タスクの期限日（null許容）。
     */
    public Task(String description, LocalDate dueDate) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("タスク内容はnullまたは空であってはなりません。");
        }
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false; // 初期状態は未完了
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("タスク内容はnullまたは空であってはなりません。");
        }
        this.description = description;
    }

    @Override
    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * タスクの文字列表現を返します。
     * 例: [ ] タスクを完了する (期限: 2025-12-31)
     * 例: [X] 完了したタスク
     * 例: [ ] 期限日なしのタスク (期限: 未設定)
     *
     * @return タスクの文字列表現。
     */
    @Override
    public String toString() {
        String status = completed ? "[X]" : "[ ]";
        String dueDateStr = (dueDate != null) ? " (期限: " + dueDate.format(DATE_FORMATTER) + ")" : " (期限: 未設定)";
        return status + " " + description + dueDateStr;
    }
}