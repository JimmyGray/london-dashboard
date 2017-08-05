import React from 'react';
import { connect } from 'react-redux';
import AppBar from 'material-ui/AppBar';
import { flattenLists } from '../transform/reduxStateTransformers';
import NewsStatusComponent from './NewsStatus/NewsStatusComponent.jsx';
import WeatherStatusComponent from './WeatherStatus/WeatherStatusComponent.jsx';
import { Responsive, WidthProvider } from 'react-grid-layout';

const ResponsiveReactGridLayout = WidthProvider(Responsive);

export class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {date: new Date()};
    }

    componentDidMount() {
        this.timerID = setInterval(
            () => this.tick(),
            1000
        );
    }

    tick() {
        this.setState({
            date: new Date()
        });
    }

    componentWillUnmount() {
        clearInterval(this.timerID);
    }


    render() {
        return (
            <div className='appContainer'>
                <AppBar
                    title={this.getAppBarTitle()}
                    showMenuIconButton={false}
                />
                <ResponsiveReactGridLayout
                    className="layout"
                    layout={this.generateLayout()}
                    verticalCompact={false} rowHeight={30} width={1200}>
                    <div key="c" data-grid={{x: 10, y: 0, w: 4, h: 2}}><WeatherStatusComponent
                        data={flattenLists(this.props.state.get('weatherRequest'))}/></div>
                    <div key="e" data-grid={{x: 10, y: 3, w: 4, h: 2}}><NewsStatusComponent
                        data={flattenLists(this.props.state.get('newsRequest'))}/></div>
                </ResponsiveReactGridLayout>
            </div>
        );
    }

    getAppBarTitle() {
        return `London Dashboard ${this.state.date}`;
    }

    generateLayout() {
        var p = this.props;
        return _.map(new Array(p.items), function (item, i) {
            var y = _.result(p, 'y') || Math.ceil(Math.random() * 4) + 1;
            return {x: i * 2 % 12, y: Math.floor(i / 6) * y, w: 2, h: y, i: i.toString()};
        });
    }
};

const mapStateToProps = (state) => ({state: state});
const mapDispatchToProps = (dispatch) => ({});
export default connect(mapStateToProps, mapDispatchToProps)(App);