
import os
from datetime import timedelta
import tempfile


BASE_DIR = os.path.dirname(os.path.realpath(__file__))


class BaseConfig:
    # Flask 
    ENV = 'development'
    FLASK_ENV = 'development'
    SECRET_KEY = '39bfea455dd5505fb8b63262a32abfe67c3a406b1a77e7e24f6bffd55ab0006bf2d31d396515c8217c159651cc463bd5999e'
    JWT_SECRET_KEY = 'NjA1MDg4ZjJhMDdiMjViYTMzNjg4MWU1ZGVjMTQwYjUyYzA0YjdiMTMyMmI3M2EyMjgyYTlkOWIxMzhmOTc0MGQ5YTM0OGFjZGE2NTg1MWRiNGQzOTFkZjc5MGRlNTliZTUxYjU5MmU4MTVkZTUxNDMzMTFlMjY3NGVjMTZmYWU='
    JWT_ALGORITHM = 'HS512'

    # Database
    SQLALCHEMY_DATABASE_URI = 'postgresql+psycopg2://<user>:<password>@<host>[:<port>]/<dbname>[?key=value&key=value...]'
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    PROPAGATE_EXCEPTIONS = False
    SQLALCHEMY_EXPIRE_ON_COMMIT = False

    # Mail Configurations
    MAIL_SERVER = 'smtp.gmail.com'
    MAIL_PORT = 587
    MAIL_USE_TLS = True
    MAIL_USE_SSL = False
    MAIL_USERNAME = 'my-email-id@gmail.com'
    MAIL_PASSWORD = 'my-email-password'

    # Cache Configurations
    
class ProdConfig:
    # Flask 
    ENV = 'production'
    FLASK_ENV = 'production'
    SECRET_KEY = '39bfea455dd5505fb8b63262a32abfe67c3a406b1a77e7e24f6bffd55ab0006bf2d31d396515c8217c159651cc463bd5999e'
    JWT_SECRET_KEY = 'NjA1MDg4ZjJhMDdiMjViYTMzNjg4MWU1ZGVjMTQwYjUyYzA0YjdiMTMyMmI3M2EyMjgyYTlkOWIxMzhmOTc0MGQ5YTM0OGFjZGE2NTg1MWRiNGQzOTFkZjc5MGRlNTliZTUxYjU5MmU4MTVkZTUxNDMzMTFlMjY3NGVjMTZmYWU='
    JWT_ALGORITHM = 'HS512'

    # Database
    # SQLALCHEMY_DATABASE_URI = 'postgresql+psycopg2://user:password@host:port/dbname[?key=value&key=value...]'
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    PROPAGATE_EXCEPTIONS = False
    SQLALCHEMY_EXPIRE_ON_COMMIT = False

    # Mail Configurations
    MAIL_SERVER = 'smtp.gmail.com'
    MAIL_PORT = 587
    MAIL_USE_TLS = True
    MAIL_USE_SSL = False
    MAIL_USERNAME = 'my-email-id@gmail.com'
    MAIL_PASSWORD = 'my-email-password'

    # Cache Configurations
