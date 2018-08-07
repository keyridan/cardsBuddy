package com.j0rsa.cardsbuddy.leo.model.flec;

import com.j0rsa.cardsbuddy.leo.model.flec.noun.NountabType;
import com.j0rsa.cardsbuddy.leo.model.flec.noun.VariantType;
import com.j0rsa.cardsbuddy.leo.model.flec.verb.*;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.j0rsa.cardsbuddy.leo.model.flec package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Html_QNAME = new QName("", "html");
    private final static QName _VerbTypeEnding_QNAME = new QName("", "ending");
    private final static QName _VerbTypePobj_QNAME = new QName("", "pobj");
    private final static QName _VerbTypeAux_QNAME = new QName("", "aux");
    private final static QName _VerbTypeRaux_QNAME = new QName("", "raux");
    private final static QName _VerbTypePref_QNAME = new QName("", "pref");
    private final static QName _VerbTypeRpron_QNAME = new QName("", "rpron");
    private final static QName _VerbTypePrep_QNAME = new QName("", "prep");
    private final static QName _VerbTypePpron_QNAME = new QName("", "ppron");
    private final static QName _VerbTypeObject_QNAME = new QName("", "object");
    private final static QName _TenseTypeCase_QNAME = new QName("", "case");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.j0rsa.cardsbuddy.leo.model.flec
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link VerbType }
     */
    public VerbType createVerbTypeWithValue() {
        return new VerbType();
    }

    /**
     * Create an instance of {@link NountabType }
     */
    public NountabType createNountabType() {
        return new NountabType();
    }

    /**
     * Create an instance of {@link HtmlType }
     */
    public HtmlType createHtmlType() {
        return new HtmlType();
    }

    /**
     * Create an instance of {@link BodyType }
     */
    public BodyType createBodyType() {
        return new BodyType();
    }

    /**
     * Create an instance of {@link HeadType }
     */
    public HeadType createHeadType() {
        return new HeadType();
    }

    /**
     * Create an instance of {@link VerbtabType }
     */
    public VerbtabType createVerbtabType() {
        return new VerbtabType();
    }

    /**
     * Create an instance of {@link EndingType }
     */
    public EndingType createEndingType() {
        return new EndingType();
    }

    /**
     * Create an instance of {@link CaseType }
     */
    public CaseType createCaseType() {
        return new CaseType();
    }

    /**
     * Create an instance of {@link PpronType }
     */
    public PpronType createPpronType() {
        return new PpronType();
    }

    /**
     * Create an instance of {@link TenseType }
     */
    public TenseType createTenseType() {
        return new TenseType();
    }

    /**
     * Create an instance of {@link ExplanationType }
     */
    public ExplanationType createExplanationType() {
        return new ExplanationType();
    }

    /**
     * Create an instance of {@link PrefType }
     */
    public PrefType createPrefType() {
        return new PrefType();
    }

    /**
     * Create an instance of {@link MoodType }
     */
    public MoodType createMoodType() {
        return new MoodType();
    }

    /**
     * Create an instance of {@link VariantType }
     */
    public VariantType createVariantType() {
        return new VariantType();
    }

    /**
     * Create an instance of {@link AuxType }
     */
    public AuxType createAuxType() {
        return new AuxType();
    }

    /**
     * Create an instance of {@link VerbType.Ppron }
     */
    public VerbType.Ppron createVerbTypePpron() {
        return new VerbType.Ppron();
    }

    /**
     * Create an instance of {@link VerbType.Ending }
     */
    public VerbType.Ending createVerbTypeEnding() {
        return new VerbType.Ending();
    }

    /**
     * Create an instance of {@link VerbType.Pref }
     */
    public VerbType.Pref createVerbTypePref() {
        return new VerbType.Pref();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HtmlType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "html")
    public JAXBElement<HtmlType> createHtml(HtmlType value) {
        return new JAXBElement<HtmlType>(_Html_QNAME, HtmlType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerbType.Ending }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "ending", scope = VerbType.class)
    public JAXBElement<VerbType.Ending> createVerbTypeEnding(VerbType.Ending value) {
        return new JAXBElement<VerbType.Ending>(_VerbTypeEnding_QNAME, VerbType.Ending.class, VerbType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "pobj", scope = VerbType.class)
    public JAXBElement<String> createVerbTypePobj(String value) {
        return new JAXBElement<String>(_VerbTypePobj_QNAME, String.class, VerbType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "aux", scope = VerbType.class)
    public JAXBElement<String> createVerbTypeAux(String value) {
        return new JAXBElement<String>(_VerbTypeAux_QNAME, String.class, VerbType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "raux", scope = VerbType.class)
    public JAXBElement<String> createVerbTypeRaux(String value) {
        return new JAXBElement<String>(_VerbTypeRaux_QNAME, String.class, VerbType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerbType.Pref }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "pref", scope = VerbType.class)
    public JAXBElement<VerbType.Pref> createVerbTypePref(VerbType.Pref value) {
        return new JAXBElement<VerbType.Pref>(_VerbTypePref_QNAME, VerbType.Pref.class, VerbType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "rpron", scope = VerbType.class)
    public JAXBElement<String> createVerbTypeRpron(String value) {
        return new JAXBElement<String>(_VerbTypeRpron_QNAME, String.class, VerbType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "prep", scope = VerbType.class)
    public JAXBElement<String> createVerbTypePrep(String value) {
        return new JAXBElement<String>(_VerbTypePrep_QNAME, String.class, VerbType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerbType.Ppron }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "ppron", scope = VerbType.class)
    public JAXBElement<VerbType.Ppron> createVerbTypePpron(VerbType.Ppron value) {
        return new JAXBElement<VerbType.Ppron>(_VerbTypePpron_QNAME, VerbType.Ppron.class, VerbType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "object", scope = VerbType.class)
    public JAXBElement<String> createVerbTypeObject(String value) {
        return new JAXBElement<String>(_VerbTypeObject_QNAME, String.class, VerbType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CaseType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "case", scope = TenseType.class)
    public JAXBElement<CaseType> createTenseTypeCase(CaseType value) {
        return new JAXBElement<CaseType>(_TenseTypeCase_QNAME, CaseType.class, TenseType.class, value);
    }

}
