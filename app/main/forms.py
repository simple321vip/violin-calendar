# 跨站攻击伪造保护
from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField, PasswordField
from wtforms.validators import DataRequired


class NameForm(FlaskForm):
    name = StringField('please input your name', validators=[DataRequired()])
    submit = SubmitField('Submit')
#password = PasswordField('please input your password', validators=[DataRequired()])