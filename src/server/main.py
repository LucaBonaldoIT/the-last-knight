from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from sqlalchemy import create_engine, Column, Integer, Text
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

# Define the SQLite database
DATABASE_URL = "sqlite:///./test.db"

engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()

# Define the model for the save file
class SaveFile(Base):
    __tablename__ = "save_files"

    id = Column(Integer, primary_key=True, index=True)
    data = Column(Text, nullable=False)

# Create the database table
Base.metadata.create_all(bind=engine)

# Define the Pydantic model for the JSON data
class SaveFileData(BaseModel):
    data: str

# Initialize the FastAPI app
app = FastAPI()

@app.get("/get-save-file")
def get_save_file():
    session = SessionLocal()
    save_file = session.query(SaveFile).first()
    session.close()
    if save_file:
        return {"data": save_file.data}
    else:
        raise HTTPException(status_code=404, detail="Save file not found")

@app.post("/set-save-file")
def set_save_file(save_file_data: SaveFileData):
    session = SessionLocal()
    save_file = session.query(SaveFile).first()
    if save_file:
        save_file.data = save_file_data.data
    else:
        save_file = SaveFile(data=save_file_data.data)
        session.add(save_file)
    session.commit()
    session.close()
    return {"message": "Save file updated successfully"}
