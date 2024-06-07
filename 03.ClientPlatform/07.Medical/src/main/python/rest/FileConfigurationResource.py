from flask import request
import logging
import json
from flask_restx import Resource, Namespace
from domain.FileConfiguration import FileConfiguration
from schema.FileConfigurationSchema import FileConfigurationSchema
from flask_jwt_extended import jwt_required
from security.SecurityUtils import has_role
from security.AuthoritiesConstants import AuthoritiesConstants
from sqlalchemy.exc import SQLAlchemyError
from marshmallow.exceptions import ValidationError

logging.basicConfig(format='%(asctime)s - %(message)s', datefmt='%d-%b-%y %H:%M:%S')
file_configurations_list_ns = Namespace('file-configurations-resource', path="/file-configurations")

file_configurations_schema = FileConfigurationSchema()
file_configurations_list_schema = FileConfigurationSchema(many=True)


class FileConfigurationResource(Resource):
    @jwt_required()
    def get(self, id):
        logging.info("GET request received on FileConfigurationResource")
        file_configurations = FileConfiguration.find_by_id(id)
        if file_configurations is not None:
            return file_configurations_schema.dump(file_configurations), 200
        return {"message": "FileConfiguration not found"}, 404

    @jwt_required()
    def put(self, id):
        logging.info("PUT request received on FileConfigurationResource")
        file_configurations_json = request.get_json()
        if file_configurations_json["id"] is None:
            return {"message": "Invalid FileConfiguration"}, 400
        if id != file_configurations_json["id"]:
            return {"message": "Invalid FileConfiguration"}, 400
        file_configurations = FileConfiguration.find_by_id(id)
        if file_configurations.get_id() is None:
            return {"message": "Invalid FileConfiguration"}, 400
        try:
            updated_file_configurations = file_configurations_schema.load(file_configurations_json, instance=file_configurations, partial=True)
        except ValidationError as err:
            return {"message": json.dumps(err.messages)}, 400
        try:
            updated_file_configurations.update_db()
        except SQLAlchemyError as e:
            return {"message": str(e.__dict__['orig'])}, 400
        return file_configurations_schema.dump(updated_file_configurations), 200
    
    @jwt_required()
    def patch(self, id):
        logging.info("PATCH request received on FileConfigurationResource")
        file_configurations_json = request.get_json()
        if file_configurations_json["id"] is None:
            return {"message": "Invalid FileConfiguration"}, 400
        if id != file_configurations_json["id"]:
            return {"message": "Invalid FileConfiguration"}, 400
        file_configurations = FileConfiguration.find_by_id(id)
        if file_configurations.get_id() is None:
            return {"message": "Invalid FileConfiguration"}, 400
        try:
            updated_file_configurations = file_configurations_schema.load(file_configurations_json, instance=file_configurations, partial=True)
        except ValidationError as err:
            return {"message": json.dumps(err.messages)}, 400
        try:
            updated_file_configurations.update_db()
        except SQLAlchemyError as e:
            return {"message": str(e.__dict__['orig'])}, 400
        return file_configurations_schema.dump(updated_file_configurations), 200

    @jwt_required()
    @has_role(AuthoritiesConstants.ADMIN)
    def delete(self, id):
        logging.info("DELETE request received on FileConfigurationResource")
        file_configurations = FileConfiguration.find_by_id(id)
        if file_configurations is None:
            return {"message": "FileConfiguration not found"}, 404
        try:
            file_configurations.delete_from_db()
        except SQLAlchemyError as e:
            return {"message": str(e.__dict__['orig'])}, 400
        return {"message": "FileConfiguration deleted"}, 204


class FileConfigurationResourceList(Resource):
    @jwt_required()
    def get(self):
        logging.info("GET request received on FileConfigurationResourceList")
        page = request.args.get('page', default=1, type=int)
        size = request.args.get('size', default=20, type=int)
        file_configurations = FileConfiguration.find_all(page, size)
        if file_configurations is not None:
            return file_configurations_list_schema.dump(file_configurations), 200
        return {"message": "FileConfiguration not found"}, 404

    @jwt_required()
    def post(self):
        logging.info("POST request received on FileConfigurationResourceList")
        file_configurations_json = request.get_json()
        try:
            file_configurations_data = file_configurations_schema.load(file_configurations_json, partial=True)
        except ValidationError as err:
            return {"message": json.dumps(err.messages)}, 400
        try:
            file_configurations_data.save_to_db()
        except SQLAlchemyError as e:
            return {"message": str(e.__dict__['orig'])}, 400
        return file_configurations_schema.dump(file_configurations_data), 201


class FileConfigurationResourceListCount(Resource):
    @jwt_required()
    def get(self):
        logging.info("GET request received on FileConfigurationResourceListCount")
        file_configurations_count = FileConfiguration.find_all_count()
        if file_configurations_count is not None:
            return file_configurations_count, 200
        return {"message": "FileConfiguration count not found"}, 404