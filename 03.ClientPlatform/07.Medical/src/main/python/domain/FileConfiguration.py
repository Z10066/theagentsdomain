from datetime import datetime
from enum import Enum
from typing import List
from DatabaseConfig import db
 


class FileConfiguration(db.Model):
    __tablename__ = "FileConfiguration"
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    name = db.Column(db.String( 255), nullable=False)
    description = db.Column(db.String( 255))
    path = db.Column(db.String( 255), nullable=False)
    types = db.Column(db.String( 255), nullable=False)

    # TODO: Adding relationships

    @classmethod
    def find_by_id(cls, _id) -> "FileConfiguration":
        return cls.query.filter_by(id=_id).first()

    @classmethod
    def find_all(cls, page, per_page) -> List["FileConfiguration"]:
        paginate = cls.query.order_by(cls.id).paginate(page=page, per_page=per_page)
        return paginate.items

    @classmethod
    def find_all_count(cls):
        return cls.query.count()
    
    def save_to_db(self) -> None:
        db.session.add(self)
        db.session.commit()

    def update_db(self) -> None:
        db.session.merge(self)
        db.session.commit()

    def delete_from_db(self) -> None:
        db.session.delete(self)
        db.session.commit()

    # Getters and setters
    def get_id(self):
        return self.id

    def set_id(self, id):
        self.id = id
    
    def get_name(self):
        return self.name

    def set_name(self, name):
        self.name = name
    
    def get_description(self):
        return self.description

    def set_description(self, description):
        self.description = description
    
    def get_path(self):
        return self.path

    def set_path(self, path):
        self.path = path
    
    def get_types(self):
        return self.types

    def set_types(self, types):
        self.types = types
