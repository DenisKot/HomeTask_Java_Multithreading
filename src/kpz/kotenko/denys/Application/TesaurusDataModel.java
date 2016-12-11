package kpz.kotenko.denys.Application;

/**
 * Created by the_m on 10.12.2016.
 */
public class TesaurusDataModel {

    public String term;

    public String definition;

    public String source;

    public TesaurusDataModel(String term, String definition, String source) {
        this.term = term;
        this.definition = definition;
        this.source = source;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.term);
        sb.append(" - ");
        sb.append(this.definition);

        if(source != null){
            sb.append(String.format(" (%1$s)", this.source));
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return term.equalsIgnoreCase(((TesaurusDataModel) obj).term);
    }

    @Override
    public int hashCode() {
        return term.hashCode() * 7;
    }
}
