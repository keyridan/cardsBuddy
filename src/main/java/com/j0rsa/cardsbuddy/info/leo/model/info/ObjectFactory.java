package com.j0rsa.cardsbuddy.info.leo.model.info;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.j0rsa.cardsbuddy.info.leo.model.generated package.
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

    private final static QName _Xml_QNAME = new QName("", "xml");
    private final static QName _ITypeM_QNAME = new QName("", "m");
    private final static QName _MTypeT_QNAME = new QName("", "t");
    private final static QName _SrTypeB_QNAME = new QName("", "b");
    private final static QName _SrTypeI_QNAME = new QName("", "i");
    private final static QName _SrTypeSup_QNAME = new QName("", "sup");
    private final static QName _ReprTypeSmall_QNAME = new QName("", "small");
    private final static QName _ReprTypeFlecttabref_QNAME = new QName("", "flecttabref");
    private final static QName _ReprTypeDomain_QNAME = new QName("", "domain");
    private final static QName _ReprTypeSr_QNAME = new QName("", "sr");
    private final static QName _SideTypeRepr_QNAME = new QName("", "repr");
    private final static QName _SideTypeIbox_QNAME = new QName("", "ibox");
    private final static QName _SideTypeWords_QNAME = new QName("", "words");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.j0rsa.cardsbuddy.info.leo.model.generated
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReprType }
     */
    public ReprType createReprType() {
        return new ReprType();
    }

    /**
     * Create an instance of {@link SideType }
     */
    public SideType createSideType() {
        return new SideType();
    }

    /**
     * Create an instance of {@link XmlInfo }
     */
    public XmlInfo createXmlInfo() {
        return new XmlInfo();
    }

    /**
     * Create an instance of {@link TType }
     */
    public TType createTType() {
        return new TType();
    }

    /**
     * Create an instance of {@link DomainType }
     */
    public DomainType createDomainType() {
        return new DomainType();
    }

    /**
     * Create an instance of {@link FfsynlistType }
     */
    public FfsynlistType createFfsynlistType() {
        return new FfsynlistType();
    }

    /**
     * Create an instance of {@link SupType }
     */
    public SupType createSupType() {
        return new SupType();
    }

    /**
     * Create an instance of {@link PronType }
     */
    public PronType createPronType() {
        return new PronType();
    }

    /**
     * Create an instance of {@link SrType }
     */
    public SrType createSrType() {
        return new SrType();
    }

    /**
     * Create an instance of {@link WordType }
     */
    public WordType createWordType() {
        return new WordType();
    }

    /**
     * Create an instance of {@link FlecttabrefType }
     */
    public FlecttabrefType createFlecttabrefType() {
        return new FlecttabrefType();
    }

    /**
     * Create an instance of {@link WordsType }
     */
    public WordsType createWordsType() {
        return new WordsType();
    }

    /**
     * Create an instance of {@link ServicedataType }
     */
    public ServicedataType createServicedataType() {
        return new ServicedataType();
    }

    /**
     * Create an instance of {@link SmallType }
     */
    public SmallType createSmallType() {
        return new SmallType();
    }

    /**
     * Create an instance of {@link SectionlistType }
     */
    public SectionlistType createSectionlistType() {
        return new SectionlistType();
    }

    /**
     * Create an instance of {@link EntryType }
     */
    public EntryType createEntryType() {
        return new EntryType();
    }

    /**
     * Create an instance of {@link IType }
     */
    public IType createIType() {
        return new IType();
    }

    /**
     * Create an instance of {@link MType }
     */
    public MType createMType() {
        return new MType();
    }

    /**
     * Create an instance of {@link CategoryType }
     */
    public CategoryType createCategoryType() {
        return new CategoryType();
    }

    /**
     * Create an instance of {@link IboxType }
     */
    public IboxType createIboxType() {
        return new IboxType();
    }

    /**
     * Create an instance of {@link SectionType }
     */
    public SectionType createSectionType() {
        return new SectionType();
    }

    /**
     * Create an instance of {@link FlecttabType }
     */
    public FlecttabType createFlecttabType() {
        return new FlecttabType();
    }

    /**
     * Create an instance of {@link ReprType.M }
     *
     */
    public ReprType.M createReprTypeM() {
        return new ReprType.M();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XmlType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "xml")
    public JAXBElement<XmlInfo> createXml(XmlInfo value) {
        return new JAXBElement<XmlInfo>(_Xml_QNAME, XmlInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "m", scope = IType.class)
    public JAXBElement<MType> createITypeM(MType value) {
        return new JAXBElement<MType>(_ITypeM_QNAME, MType.class, IType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "t", scope = MType.class)
    public JAXBElement<TType> createMTypeT(TType value) {
        return new JAXBElement<TType>(_MTypeT_QNAME, TType.class, MType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "b", scope = SrType.class)
    public JAXBElement<String> createSrTypeB(String value) {
        return new JAXBElement<String>(_SrTypeB_QNAME, String.class, SrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "i", scope = SrType.class)
    public JAXBElement<IType> createSrTypeI(IType value) {
        return new JAXBElement<IType>(_SrTypeI_QNAME, IType.class, SrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "m", scope = SrType.class)
    public JAXBElement<MType> createSrTypeM(MType value) {
        return new JAXBElement<MType>(_ITypeM_QNAME, MType.class, SrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SupType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "sup", scope = SrType.class)
    public JAXBElement<SupType> createSrTypeSup(SupType value) {
        return new JAXBElement<SupType>(_SrTypeSup_QNAME, SupType.class, SrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "b", scope = ReprType.M.class)
    public JAXBElement<String> createReprTypeMB(String value) {
        return new JAXBElement<String>(_SrTypeB_QNAME, String.class, ReprType.M.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "b", scope = SmallType.class)
    public JAXBElement<String> createSmallTypeB(String value) {
        return new JAXBElement<String>(_SrTypeB_QNAME, String.class, SmallType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "i", scope = SmallType.class)
    public JAXBElement<IType> createSmallTypeI(IType value) {
        return new JAXBElement<IType>(_SrTypeI_QNAME, IType.class, SmallType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "m", scope = SmallType.class)
    public JAXBElement<MType> createSmallTypeM(MType value) {
        return new JAXBElement<MType>(_ITypeM_QNAME, MType.class, SmallType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SupType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "sup", scope = SmallType.class)
    public JAXBElement<SupType> createSmallTypeSup(SupType value) {
        return new JAXBElement<SupType>(_SrTypeSup_QNAME, SupType.class, SmallType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SmallType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "small", scope = ReprType.class)
    public JAXBElement<SmallType> createReprTypeSmall(SmallType value) {
        return new JAXBElement<SmallType>(_ReprTypeSmall_QNAME, SmallType.class, ReprType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlecttabrefType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "flecttabref", scope = ReprType.class)
    public JAXBElement<FlecttabrefType> createReprTypeFlecttabref(FlecttabrefType value) {
        return new JAXBElement<FlecttabrefType>(_ReprTypeFlecttabref_QNAME, FlecttabrefType.class, ReprType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DomainType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "domain", scope = ReprType.class)
    public JAXBElement<DomainType> createReprTypeDomain(DomainType value) {
        return new JAXBElement<DomainType>(_ReprTypeDomain_QNAME, DomainType.class, ReprType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReprType.M }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "m", scope = ReprType.class)
    public JAXBElement<ReprType.M> createReprTypeM(ReprType.M value) {
        return new JAXBElement<ReprType.M>(_ITypeM_QNAME, ReprType.M.class, ReprType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SrType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "sr", scope = ReprType.class)
    public JAXBElement<SrType> createReprTypeSr(SrType value) {
        return new JAXBElement<SrType>(_ReprTypeSr_QNAME, SrType.class, ReprType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReprType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "repr", scope = SideType.class)
    public JAXBElement<ReprType> createSideTypeRepr(ReprType value) {
        return new JAXBElement<ReprType>(_SideTypeRepr_QNAME, ReprType.class, SideType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IboxType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "ibox", scope = SideType.class)
    public JAXBElement<IboxType> createSideTypeIbox(IboxType value) {
        return new JAXBElement<IboxType>(_SideTypeIbox_QNAME, IboxType.class, SideType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WordsType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "words", scope = SideType.class)
    public JAXBElement<WordsType> createSideTypeWords(WordsType value) {
        return new JAXBElement<WordsType>(_SideTypeWords_QNAME, WordsType.class, SideType.class, value);
    }
}
