package model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Pets {
      private Long id;
	  private Category category;
	  private String name;
	  private List<String> photoUrls = new ArrayList<String>();
	  private List<Tag> tags = new ArrayList<>();
	  private Status status; 
	  //getters for id,name and Status
	  
	  public long getId()
	  {
		  return this.id;
	  }
	  
	  public Status getStatus()
	  {
		  return this.status;
	  }
	  public String getName()
	  {
		  return this.name;
	  }
	  public void setId(Long id)
	  {
		  this.id =id;
		  
	  }
     public void setStatus(Status status)
     {
    	 this.status = status;
     }
     public void setName(String name)
     {
    	 this.name = name;
     }
     public void setPhotoUrls(String s)
     {
     	this.photoUrls.add(s);
     }
    
     public String[] getPhotoUrls()
     {   
    	 String[] photoUrlsArray = new String[photoUrls.size()];
    	 photoUrls.toArray(photoUrlsArray);
    	 return photoUrlsArray;
     }
       
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pets pet = (Pets) o;
        return Objects.equals(this.id, pet.id) &&
                Objects.equals(this.category, pet.category) &&
                Objects.equals(this.name, pet.name) &&
                Objects.equals(this.photoUrls, pet.photoUrls) &&
                Objects.equals(this.status, pet.status);
    }

    public static class Builder {
        private Long id;
        private Category category;
        private String name;
        private List<String> photoUrls = new ArrayList<>();
        private List<Tag> tags = new ArrayList<>();
        private Status status;

        public Builder() {

        }
   
        public Builder withId(Long id) {
            this.id = id;
            return this;
        }
       
      
        public Builder inCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPhotoUrls(List<String> photoUrls) {
            this.photoUrls = photoUrls;
            return this;
        }

        public Builder withTags(List<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Pets build() {
            Pets pet = new Pets();
            pet.id = this.id;
            pet.name = this.name;
            pet.category = this.category;
            pet.photoUrls = this.photoUrls;
            pet.tags = this.tags;
            pet.status = this.status;
            return pet;
        }

    }

}

