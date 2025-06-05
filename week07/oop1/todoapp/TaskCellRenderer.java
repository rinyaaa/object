// TaskCellRenderer.java
package week07.oop1.todoapp;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

/**
 * JListのタスク項目をカスタマイズして表示するためのセルレンダラーです。
 * 完了したタスクに取り消し線とグレーの文字色を適用します。
 */
class TaskCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList<?> list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        // 親クラスの実装を呼び出し、基本的なJLabelコンポーネントを取得
        // これにより、選択状態やフォーカス状態に応じたデフォルトのスタイルが適用されます。
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // valueがTaskオブジェクトの場合にカスタマイズを適用
        if (value instanceof Task) {
            Task task = (Task) value;

            // TaskオブジェクトのtoString()メソッドを利用して表示テキストを設定
            label.setText(task.toString());

            // タスクが完了している場合、視覚的スタイルを変更
            if (task.isCompleted()) {
                label.setForeground(Color.GRAY); // 文字色をグレーに設定

                // フォント属性を操作して取り消し線を追加
                Map<TextAttribute, Object> attributes = new HashMap<>(label.getFont().getAttributes());
                attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                label.setFont(label.getFont().deriveFont(attributes));
            } else {
                // 未完了のタスクはデフォルトのスタイルに戻す
                label.setForeground(list.getForeground()); // JListの標準の前景色
                Map<TextAttribute, Object> attributes = new HashMap<>(label.getFont().getAttributes());
                attributes.put(TextAttribute.STRIKETHROUGH, false); // 取り消し線を解除
                label.setFont(label.getFont().deriveFont(attributes));
            }
        }
        return label;
    }
}