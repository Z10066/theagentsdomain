from marshmallow_sqlalchemy import auto_field, fields
from WebSerializer import ma
from DatabaseConfig import db
from domain.FileConfiguration import FileConfiguration


class FileConfigurationSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = FileConfiguration
        load_instance = True
        exclude = (
        )
        sqla_session = db.session
        
