package oop1.section08.kadai3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ValidatedTextField extends JTextField {
    private final InputValidator validator;

    public ValidatedTextField(InputValidator validator, int columns) {
        super(columns);
        this.validator = validator;
        addFocusListener(new ValidationFocusListener());
    }

    // フォーカスリスナーの内部クラス
    private class ValidationFocusListener extends FocusAdapter {
        @Override
        public void focusLost(FocusEvent e) {
            try {
                // 入力値を検証
                validator.validate(getText());
                // 検証成功時は背景色をデフォルトに戻す
                setBackground(Color.WHITE);
            } catch (ValidationException ex) {
                // 検証失敗時はエラーメッセージを表示し、背景色を赤にする
                JOptionPane.showMessageDialog(ValidatedTextField.this,
                        ex.getMessage(),
                        "入力エラー",
                        JOptionPane.ERROR_MESSAGE);
                setBackground(Color.PINK); // エラーを示す色
            }
        }

        @Override
        public void focusGained(FocusEvent e) {
            // フォーカスを得たときに背景色をデフォルトに戻す（エラー状態を解除）
            setBackground(Color.WHITE);
        }
    }
}